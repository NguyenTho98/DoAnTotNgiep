import React, { useState, useEffect } from 'react';
import moment from 'moment';
import { default_option } from '../../../common/filterConstants';
import FilterWrapper from "./components/FilterWrapper";
import FilterOption from "./components/FilterOption";
import "./filterTermsModal.scss";

function FilterTermsModal(props) {

  const defaultStage = (type) => {
    if (type === "time") {
      return({
        startTime: '',
        endTime: '',
      })
    } else if (type === "phone") {
      return({
        status: false,
        hasPhone: false,
      })
    } else if (type === "filterInfo") {
      return({
        opens: 'center',
        drops: 'down',
        ranges: {
          'Hôm nay': [moment(), moment()],
          'Hôm qua': [moment()
            .subtract(1, 'days'), moment()
            .subtract(1, 'days')],
          '7 ngày trước': [moment()
            .subtract(6, 'days'), moment()],
          '30 ngày trước': [moment()
            .subtract(29, 'days'), moment()],
          '90 ngày trước': [moment()
            .subtract(89, 'days'), moment()],
        },
        locale: {
          applyLabel: 'Áp dụng',
          cancelLabel: 'Hủy',
          format: 'DD/MM/YYYY',
          daysOfWeek: [
            'CN',
            'T2',
            'T3',
            'T4',
            'T5',
            'T6',
            'T7',
          ],
          monthNames: [
            'tháng 1',
            'tháng 2',
            'tháng 3',
            'tháng 4',
            'tháng 5',
            'tháng 6',
            'tháng 7',
            'tháng 8',
            'tháng 9',
            'tháng 10',
            'tháng 11',
            'tháng 12',
          ],
          separator: ' - ',
        },
      })
    }
    return({
      status: false,
      hasAddress: false,
    })

  }
  const [selectedFilter, setSelectedFilter] = useState([]);
  const [filterPhone, setFilterPhone] = useState(defaultStage('phone'));
  const [filterAddress, setFilterAddress] = useState(defaultStage('address'));
  const [time, setTime] = useState(defaultStage('time'));
  const [filterInfo, setFilterInfo] = useState(defaultStage('filterInfo'));

  const getOptionName = option => {
    switch (option) {
      case 'select': return 'Chọn điều kiện lọc';
      case 'phone': return 'Số điện thoại';
      case 'address': return 'Địa chỉ';
      case 'time': return 'Thời gian bình luận';
      default: return null;
    }
  };

  const getUnSelectedOption = () => {
    if (selectedFilter.length === 0) {
      return default_option;
    }
    if (selectedFilter.length === default_option.length) {
      return [];
    }
    return default_option.filter(option => !selectedFilter.includes(option));
  };

  const resetFilter = (option) => {
    switch (option) {
      case 'phone':
        setFilterPhone(defaultStage('phone'));
        break;
      case 'address':
        setFilterAddress(defaultStage('address'));
        break;
      case 'time':
        setTime(defaultStage('time'));
        break;
      default:
        break;
    }
  }

  const selectOption = option => {
    console.log('option', option);
    // if (option === 'select') return;
    const newSelectedFilter = selectedFilter;
    let exist = false;
    newSelectedFilter.forEach((item, index) => {
      if (item === option) {
        newSelectedFilter.slice(index, 1);
        resetFilter(option);
        exist = true;
        return false;
      }
    })
    if (!exist) newSelectedFilter.push(option);
    setSelectedFilter(newSelectedFilter);
  };

  const handleApply = (event, picker) => {
    const currentTime = {
      startTime: picker.startDate,
      endTime: picker.endDate,
    }
    setTime(currentTime);
  }

  const cancel = () => {
    setTime(defaultStage('time'));
  };

  const getTimeInfo = () => {
    if (time.startTime && time.endTime) {
      return `Từ ${moment(time.startTime).format('DD/MM/YYYY')} đến ${moment(time.endTime).format('DD/MM/YYYY')}`;
    }
    return 'Chọn thời gian bình luận';
  }

  const filterAction = (option, has) => {
    if (option === 'phone') {
      const status = !(filterPhone.status && filterPhone.hasPhone === has);
      setFilterPhone({
        status,
        hasPhone: has,
      });
    }
    if (option === 'address') {
      const status = !(filterAddress.status && filterAddress.hasAddress === has);
      setFilterAddress({
        status,
        hasAddress: has,
      });
    }
  }

  // Action when click 'Lọc'
  const handleFilter = () => {
    const selectedFilter = [];
    for (let i = 0; i < selectedFilter.length; i++) {
      const option = selectedFilter[i];
      if (option !== 'time') {
        selectedFilter.push(option);
      }
      if (option === 'time' && time.startTime && time.endTime) {
        selectedFilter.push(option);
      }
    }

    const editFilterInfo = {
      selectedFilter,
      hasPhone: filterPhone.status ? filterPhone.hasPhone : null,
      hasAddress: filterAddress.status ? filterAddress.hasAddress : null,
      start: time.startTime,
      endTime: time.endTime,
      filterText: filterInfo.filterText,
      showNo: !!selectedFilter.length
    };
  };

  const changeOption = (newOption, oldOption) => {
    const oldSelectedFilter = selectedFilter;
    const newSelectedFilter = [];
    oldSelectedFilter.forEach(item => {
      if (item === oldOption) {
        newSelectedFilter.push(newOption);
      } else newSelectedFilter.push(item);
    })
    resetFilter(oldOption);
    setSelectedFilter(newSelectedFilter);
  };

  const lastFilterOption = ['select'].concat(getUnSelectedOption());

  return (
    <div id="filter-modal-wrapper">
      <div id="filter-modal-header">
        Hiển thị bài viết theo
      </div>
      <div id="filter-modal-body">
        {/* <FilterWrapper 
        selectedFilter={selectedFilter}
        getUnSelectedOption={getUnSelectedOption}
        selectOption={selectOption}
        getOptionName={getOptionName}
        changeOption={changeOption}
        /> */}
        {lastFilterOption.length !== 1 ? (
          <div className="filter-line">
            <FilterOption
              selectedFilter={lastFilterOption}
              selectedOption={'select'}
              selectOption={selectOption}
              getOptionName={getOptionName}
              changeOption={changeOption}
            />
          </div>
        ) : null}
      </div>
      <div id="filter-modal-footer">
        <button type="button" id="filter-modal-action" onClick={() => handleFilter}>
          Lọc
        </button>
      </div>
    </div>
  );
}

export default FilterTermsModal;
