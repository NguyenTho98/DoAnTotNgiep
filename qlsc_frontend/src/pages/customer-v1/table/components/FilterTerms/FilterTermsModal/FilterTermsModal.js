import React, { useState, useEffect } from "react";
import { default_option } from "../../../common/filterConstants";
import FilterOption from "./components/FilterOption";
import FilterWrapper from "./components/FilterWrapper";
import "./filterTermsModal.scss";

function FilterTermsModal(props) {
  const defaultStage = (type) => {
    if (type === "time") {
      return {
        startTime: "",
        endTime: "",
      };
    } else if (type === "phone") {
      return {
        status: false,
        hasPhone: false,
      };
    } 
    return {
      status: false,
      hasAddress: false,
    };
  };

  const [selectedFilter, setSelectedFilter] = useState([]);
  const [filterPhone, setFilterPhone] = useState(defaultStage("phone"));
  const [filterAddress, setFilterAddress] = useState(defaultStage("address"));
  const [time, setTime] = useState(defaultStage("time"));
  const [lastFilterOption, setLastFilterOption] = useState([]);

  useEffect(() => {
    setLastFilterOption(["select"].concat(getUnSelectedOption()));
  }, [selectedFilter]);

  const getOptionName = (option) => {
    switch (option) {
      case "select":
        return "Chọn điều kiện lọc";
      case "phone":
        return "Số điện thoại";
      case "address":
        return "Địa chỉ";
      case "time":
        return "Thời gian";
      default:
        return null;
    }
  };

  const getUnSelectedOption = () => {
    if (selectedFilter.length === 0) {
      return default_option;
    }
    if (selectedFilter.length === default_option.length) {
      return [];
    }
    return default_option.filter((option) => !selectedFilter.includes(option));
  };

  const resetFilter = (option) => {
    switch (option) {
      case "phone":
        setFilterPhone(defaultStage("phone"));
        break;
      case "address":
        setFilterAddress(defaultStage("address"));
        break;
      case "time":
        setTime(defaultStage("time"));
        break;
      default:
        break;
    }
  };

  const selectOption = (option) => {
    if (option === "select") return;
    const newSelectedFilter = [...selectedFilter];
    let exist = false;
    newSelectedFilter.forEach((item, index) => {
      if (item === option) {
        newSelectedFilter.slice(index, 1);
        resetFilter(option);
        exist = true;
        return false;
      }
    });
    if (!exist) newSelectedFilter.push(option);
    setSelectedFilter(newSelectedFilter);
  };

  const filterAction = (option, has) => {
    if (option === "phone") {
      const status = !(filterPhone.status && filterPhone.hasPhone === has);
      setFilterPhone({
        status,
        hasPhone: has,
      });
    }
    if (option === "address") {
      const status = !(
        filterAddress.status && filterAddress.hasAddress === has
      );
      setFilterAddress({
        status,
        hasAddress: has,
      });
    }
  };

  // Action when click 'Lọc'
  const handleFilter = () => {
    const selectedFilter = [];
    for (let i = 0; i < selectedFilter.length; i++) {
      const option = selectedFilter[i];
      if (option !== "time") {
        selectedFilter.push(option);
      }
      if (option === "time" && time.startTime && time.endTime) {
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
      showNo: !!selectedFilter.length,
    };
  };

  const changeOption = (newOption, oldOption) => {
    const oldSelectedFilter = selectedFilter;
    const newSelectedFilter = [];
    oldSelectedFilter.forEach((item) => {
      if (item === oldOption) {
        newSelectedFilter.push(newOption);
      } else newSelectedFilter.push(item);
    });
    resetFilter(oldOption);
    setSelectedFilter(newSelectedFilter);
  };

  return (
    <div id="filter-modal-wrapper">
      <div id="filter-modal-header">Hiển thị khách hàng theo</div>
      <div id="filter-modal-body">
        <FilterWrapper
          time={time}
          filterPhone={filterPhone}
          filterAddress={filterAddress}
          selectedFilter={selectedFilter}
          filterAction={filterAction}
          getUnSelectedOption={getUnSelectedOption}
          selectOption={selectOption}
          getOptionName={getOptionName}
          changeOption={changeOption}
        />
        {lastFilterOption.length !== 1 ? (
          <div className="filter-line">
            <FilterOption
              selectedFilter={lastFilterOption}
              selectedOption={"select"}
              selectOption={selectOption}
              getOptionName={getOptionName}
              changeOption={changeOption}
            />
          </div>
        ) : null}
      </div>
      <div id="filter-modal-footer">
        <button
          type="button"
          id="filter-modal-action"
          onClick={() => handleFilter}
        >
          Lọc
        </button>
      </div>
    </div>
  );
}

export default FilterTermsModal;
