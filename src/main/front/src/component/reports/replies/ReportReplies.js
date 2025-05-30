import ReportRepliesList from "./ReportRepliesList";
import ReportRepliesWrite from "./ReportRepliesWrite";
import {useParams} from "react-router-dom";


function ReportReplies() {

    const reportId = useParams().reportId

    return (
        <>
            <section className={"templateSection"}>
                    <ReportRepliesList reportId={reportId}/>
                    <ReportRepliesWrite reportId={reportId}/>
            </section>
        </>
    )
}

export default ReportReplies