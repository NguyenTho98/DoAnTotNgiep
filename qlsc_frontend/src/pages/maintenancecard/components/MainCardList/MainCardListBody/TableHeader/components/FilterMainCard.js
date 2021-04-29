/* eslint-disable no-shadow */
/* eslint-disable no-undef */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import debounce from 'utils/debounce';
import '../styles/filterMainCard.scss';
import * as Icons from 'pages/maintenancecard/commons/Icons';
import { default_option, default_status_work, default_status_work_detail, default_status_payment, default_status_payment_detail } from 'pages/maintenancecard/commons/mainCardConstants.js';
import { showFilter } from '../../../../../actions/mainCard';

function FilterMainCard(props) {
  const { showFilter } = props;
  const [filterText, setFilterText] = useState('');

  useEffect(() => {
    const { filterInfo, fetching } = props;
    if (filterText !== filterInfo.filterText && !fetching) {
      setFilterText(filterText);
    }
  }, [filterText]);

  const onShowFilter = () => {
    showFilter(!props.filterInfo.showFilter);
  };

  const removeFilter = (filterName) => {
    const { filterInfo } = props;
    const newSelectedFilter = [];
    for (let i = 0; i < filterInfo.selectedFilter.length; i++) {
      if (filterInfo.selectedFilter[i] !== filterName) {
        newSelectedFilter.push(filterInfo.selectedFilter[i]);
      }
    }
    filterInfo.selectedFilter = newSelectedFilter;
    if (filterName === default_option[0]) {
      filterInfo.status = '';
    }
    if (filterName === default_option[1]) {
      filterInfo.selectedLocation = [];
    }
    // props.fetchOrderCollation(filterInfo);
  };

  const searchChange = debounce((e) => search(e), 400);

  const onChangeText = (e) => {
    e.persist();
    setFilterText(e.target.value);
    searchChange(e.target.value);
  };

  const getFilterText = (filterName) => {
    if (filterName === 'statusWork') {
      for (let i = 0; i < default_status.length; i++) {
        if (props.filterInfo.status === default_status_work[i]) {
          return `Trạng thái công việc: ${default_status_work_detail[i]}`;
        }
      }
    }
    if (filterName === 'statusPayment') {
      for (let i = 0; i < default_status.length; i++) {
        if (props.filterInfo.status === default_status_payment[i]) {
          return `Trạng thái thanh toán: ${default_status_payment_detail[i]}`;
        }
      }
    }
    if (filterName === 'datetime') {
      return `Ngày tạo: Từ 01/09/1998- 17/09/1998`;
    }
    return null;
  };

  const search = (e) => {
    const { filterInfo } = props;
    filterInfo.filterText = e;
    // props.fetchOrderCollation(filterInfo);
  };
  return (
    <div id="filter-delivery-collations-wrapper">
      <div id="filter-delivery-collations-by-tab-wrapper">
        <ul id="filter-delivery-collations-by-tab">
          <li
            className="filter-delivery-collations-tab active"
          >
              Tất cả phiếu sửa chữa
          </li>
        </ul>
      </div>
      <div id="filter-delivery-collations-option-wrapper">
        <button type="button" id="filter-delivery-collations-button"
          onClick={() => onShowFilter()}
        >
            Lọc phiếu sửa chữa
          <Icons.ArrowDown />
        </button>
        <div id="filter-delivery-collations-search">
          <div id="filter-delivery-collations-search-icon">
            <Icons.Search />
          </div>
          <input
            id="filter-delivery-collations-search-input"
            placeholder="Tìm kiếm phiếu sữa chữa theo mã phiếu sữa chữa"
            value={filterText}
            onChange={(e) => onChangeText(e)}
          />
        </div>
      </div>
      <div id="filter-delivery-collations-info-wrapper">
        {props.filterInfo && props.filterInfo.selectedFilter && props.filterInfo.selectedFilter.map((filterName, index) => {
          const filterText = getFilterText(filterName);
          if (!filterText) return null;
          return (
            <div className="filter-delivery-collations-option-info" key={index}>
              <div className="text-ellipsis" style={{ maxWidth: 300 }}>
                {filterText}
              </div>
              <div onClick={() => removeFilter(filterName)}>
                <Icons.Exit />
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

FilterMainCard.defaultProps = {
};
const mapStateToProps = state => {
  const { mainCards: { filterInfo, ui: { fetching } } } = state;
  return {
    filterInfo,
    fetching,
  };
};

const mapDispatchToProps = (dispatch) => ({
  showFilter: (show) => dispatch(showFilter(show)),
  fetchOrderCollation: (filter, page) => dispatch(fetchOrderCollation(filter, page))
});

export default connect(mapStateToProps, mapDispatchToProps)(FilterMainCard);
