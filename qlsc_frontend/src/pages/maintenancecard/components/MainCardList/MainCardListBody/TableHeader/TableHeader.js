import React from 'react';
import { connect } from 'react-redux';

import FilterMainCard from './components/FilterMainCard';
import FilterMainCardModal from './components/FilterMainCardModal';

function TableHeader(props) {
  const { showFilter } = props;
  return (
    <div id="delivery-collations-table-header" style={{ position: 'relative' }}>
      <FilterMainCard />
      {showFilter ? <FilterMainCardModal /> : null}
    </div>
  );
}

const mapStateToProps = state => {
  const { mainCard: { filterInfo } } = state;
  const showFilter = filterInfo && filterInfo.showFilter;
  return {
    showFilter,
  };
};

export default connect(mapStateToProps, null)(TableHeader);
