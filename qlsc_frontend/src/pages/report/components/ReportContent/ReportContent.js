import React, { useState, useEffect } from "react";
import ReportLeft from "./ReportLeft/ReportLeft";
import ReportRight from "./ReportRight/ReportRight";

import "./styles.scss";
import LineLeft from "./components/LineLeft";
import LineRight from "./components/LineRight";

function ReportConent(props) {
  const { data } = props;
  return (
    <div className="report-content-container">
      <div className="row report-general-order-container">
        <div className="col-md-7" style={{ paddingRight: 0 }}>
          <ReportLeft data={data} />
        </div>
        <div className="col-md-5">
          <ReportRight data={data.businessToday} />
        </div>
      </div>

      <div
        className="row report-general-order-container"
        style={{ paddingTop: 15 }}
      >
        <div className="col-md-7" style={{ paddingRight: 0 }}>
          <LineLeft data={data.topStaffs || []} />
        </div>
        <div className="col-md-5">
          <LineRight data={data.topServices || []} />
        </div>
      </div>
    </div>
  );
}

export default ReportConent;
