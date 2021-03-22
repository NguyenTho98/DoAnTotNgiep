import React from 'react';
import FilterOption from './FilterOption';
import Options from '../components/Options';

function FilterRow(props) {
  const { option, getOptionName, changeOption, selectOption } = props;
  const listOption = props.getUnSelectedOption();
  return (
    <div className="filter-line" style={{ display: 'flex' }}>
      <FilterOption 
      selectedFilter={listOption}
      selectOption={option}
      getOptionName={getOptionName}
      changeOption={changeOption}
      />
      <Options option={option} />
      <div className="filter-bin" onClick={() => selectOption(option)}>
        <Icon.Bin />
      </div>
    </div>
  );
}

export default FilterRow;
