import React, { useState, useEffect } from 'react';
import Filter from './Filter/Filter';
import { connect } from "react-redux";
import { getDataForReport } from "../actions/reportAction";
import './styles.scss';
import ReportContent from './ReportContent/ReportContent';

function ReportMain(props) {
  const { onGetDataForReport } = props;
  const [data, setData] = useState({});

  const onGetDataByFilter = (from, to) => {
    onGetDataForReport(from, to).then((json) => {
      if (json) setData(json);
    });
  }

  return (
    <div className="report-main-container">
      <Filter onGetDataByFilter={onGetDataByFilter} />
      <ReportContent data={data} />
    </div>
  );
}
const mapDispatchToProps = (dispatch) => ({
  onGetDataForReport: (from, to) => dispatch(getDataForReport(from, to)),
});
export default connect(null, mapDispatchToProps)(ReportMain);
