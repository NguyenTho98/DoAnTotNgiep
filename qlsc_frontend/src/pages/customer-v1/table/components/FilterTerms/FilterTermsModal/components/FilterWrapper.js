import React from 'react';
import FilterRow from './FilterRow';

function FilterWrapper(props) {
  const {
    time,
    filterPhone,
    filterAddress,
    selectedFilter,
    filterAction,
    getUnSelectedOption,
    selectOption,
    getOptionName,
    changeOption,
  } = props;

    return (
      <React.Fragment>
        {
           selectedFilter.length ? selectedFilter.map((option, index) =>
             (
              <div key={index}>
              <FilterRow
                time={time}
                option={option}
                filterPhone={filterPhone}
                filterAddress={filterAddress}
                selectedFilter={selectedFilter}
                filterAction={filterAction}
                getUnSelectedOption={getUnSelectedOption}
                selectOption={selectOption}
                getOptionName={getOptionName}
                changeOption={changeOption}
                />
            </div>
            )) : ''
        }
      </React.Fragment>
    );
}

export default FilterWrapper;
