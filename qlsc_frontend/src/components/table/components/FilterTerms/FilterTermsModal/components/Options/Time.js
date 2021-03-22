import React from 'react';
import DatetimeRangePicker from 'react-bootstrap-datetimerangepicker';

function Time(props) {
  return (
    <span className="filter-feed-comment-detail">
            <DatetimeRangePicker
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
            </DatetimeRangePicker>
          </span>
  );
}

export default Time;
