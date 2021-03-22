import React from 'react';
import FilterRow from './FilterRow';

function FilterWrapper(props) {
  const { selectedFilter, getUnSelectedOption, selectOption, getOptionName } = props;
  console.log('selectedFilter', selectedFilter);
  selectedFilter.map((option, index) => {
    return (
      <div key={index}>
        <FilterRow
        option={option} 
        getUnSelectedOption={getUnSelectedOption}
        selectOption={selectOption}
        getOptionName={getOptionName}
        changeOption={changeOption}
        />
      </div>
    );
  });
}

export default FilterWrapper;
