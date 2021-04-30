import React from 'react';
import { connect } from 'react-redux';

import FilterCustomer from './components/FilterCustomer';
import FilterCustomerModal from './components/FilterCustomerModal';

function TableHeader(props) {
  const { showFilter } = props;
  return (
    <div id="delivery-collations-table-header" style={{ position: 'relative' }}>
      <FilterCustomer />
      {showFilter ? <FilterCustomerModal /> : null}
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
