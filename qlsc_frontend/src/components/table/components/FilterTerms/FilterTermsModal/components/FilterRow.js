import React from 'react';
import FilterOption from './FilterOption';
import Options from '../components/Options';
import * as Icons from '../../../../Icons/Icons';

function FilterRow(props) {

  const {
    time,
    filterPhone,
    filterAddress,
    selectedFilter,
    option,
    getOptionName,
    changeOption,
    selectOption,
    filterAction
  } = props;

  const listOption = props.getUnSelectedOption();

  return (
    <div className="filter-line" style={{ display: 'flex' }}>
      <FilterOption
      selectedFilter={listOption}
      selectedOption={option}
      getOptionName={getOptionName}
      changeOption={changeOption}
      />
      <Options
        time={time}
        option={option}
        filterPhone={filterPhone}
        filterAddress={filterAddress}
        selectedFilter={selectedFilter}
        filterAction={filterAction}
      />
      <div className="filter-bin" onClick={() => selectOption(option)}>
         <Icons.Bin />
      </div>
    </div>
  );
}

export default FilterRow;
