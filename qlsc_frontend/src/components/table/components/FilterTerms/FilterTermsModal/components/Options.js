import React from 'react';
import Phone from "./Options/Phone";
import Address from "./Options/Address";

function Options(props) {
  const { option } = props;
  if (option === 'phone') {
    return (<Phone />);
  } else if (option === 'address') {
    return (<Address />);
  } else if (option === 'time') {
    return (
      <span className="filter-detail">
      {/* <DatetimeRangePicker
        opens={this.state.opens}
        drops={this.state.drops}
        startDate={this.state.beginTime}
        endDate={this.state.endTime}
        onApply={this.handleApply}
        linkedCalendars={this.state.linkedCalendars}
        onCancel={this.cancel}
        ranges={this.state.ranges}
        locale={this.state.locale}
        alwaysShowCalendars
      >
        <button type="text" className="form-control dateRange" style={{ marginLeft: 10, width: 284, cursor: 'pointer' }}>{this.getTimeInfo()}</button>
      </DatetimeRangePicker> */}
    </span>
    )
  }
  return null;
}

export default Options;
