import React from 'react';
import FilterByDate from './FilterByDate/FilterByDate';
import "./styles.scss";

function Filter(props) {
  const { onGetDataByFilter } = props;
  return (
    <div className="d-flex flex-wrap align-items-center">
      <div className="title-report-dashboard">Tổng quan báo cáo</div>
      <FilterByDate onGetDataByFilter={onGetDataByFilter} />
    </div>
  );
}

export default React.memo(Filter);
