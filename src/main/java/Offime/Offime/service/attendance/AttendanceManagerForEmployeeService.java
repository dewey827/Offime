package Offime.Offime.service.attendance;

import Offime.Offime.dto.response.attendance.ResAttendanceHistoryForEmployeeDto;
import Offime.Offime.dto.response.attendance.ResAttendanceRecordDto;
import Offime.Offime.entity.attendance.EventRecord;
import Offime.Offime.entity.member.Member;
import Offime.Offime.repository.attendance.EventRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceManagerForEmployeeService {

    private final EventRecordRepository eventRecordRepository;

    public ResAttendanceRecordDto getDailyAttendanceRecord(Member member, LocalDate date) {
        List<EventRecord> records = eventRecordRepository.findByMemberAndDate(member, date);
        return ResAttendanceRecordDto.fromEntity(records, date);
    }

    public ResAttendanceHistoryForEmployeeDto getWeeklyAttendanceHistory(Member member, LocalDate date) {
        LocalDate startOfWeek = date.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        List<EventRecord> records = eventRecordRepository.findByMemberAndDateBetween(member, startOfWeek, endOfWeek);

        List<LocalDate> weekdays = startOfWeek.datesUntil(endOfWeek.plusDays(1))
                .filter(d -> !d.getDayOfWeek().equals(java.time.DayOfWeek.SATURDAY) &&
                        !d.getDayOfWeek().equals(java.time.DayOfWeek.SUNDAY))
                .toList();

        LocalDate today = LocalDate.now();
        return ResAttendanceHistoryForEmployeeDto.fromEntity(records, weekdays, today);
    }

    public ResAttendanceHistoryForEmployeeDto getMonthlyAttendanceHistory(Member member, int year, int month) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);

        List<EventRecord> records = eventRecordRepository.findByMemberAndDateBetween(member, startOfMonth, endOfMonth);

        List<LocalDate> weekdaysInMonth = startOfMonth.datesUntil(endOfMonth.plusDays(1))
                .filter(date -> !date.getDayOfWeek().equals(java.time.DayOfWeek.SATURDAY) &&
                        !date.getDayOfWeek().equals(java.time.DayOfWeek.SUNDAY))
                .toList();

        LocalDate today = LocalDate.now();

        return ResAttendanceHistoryForEmployeeDto.fromEntity(records, weekdaysInMonth, today);
    }
}