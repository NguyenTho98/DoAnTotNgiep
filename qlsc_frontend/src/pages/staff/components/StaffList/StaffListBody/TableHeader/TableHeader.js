import React from 'react';
import { connect } from 'react-redux';

import FilterStaff from './components/FilterStaff';

function TableHeader(props) {
  const { showFilter } = props;
  return (
    <div id="delivery-collations-table-header" style={{ position: 'relative' }}>
      <FilterStaff />
    </div>
  );
}

const mapStateToProps = state => {
  const { staffs: { filterInfo } } = state;
  const showFilter = filterInfo && filterInfo.showFilter;
  return {
    showFilter,
  };
};

export default connect(mapStateToProps, null)(TableHeader);
