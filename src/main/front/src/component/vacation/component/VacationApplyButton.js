const VacationApplyButton = ({ handleApply }) => {
  return (
    <div
      style={{
        position: "fixed",
        bottom: "20px",
        left: "0px",
        width: "100%",
        display: "flex",
        justifyContent: "center",
      }}
    >
      <button
        className="btn btn-pm btn-lg fs_md mb_md"
        onClick={handleApply}
      >
        신청하기
      </button>
    </div>
  );
};
export default VacationApplyButton;
