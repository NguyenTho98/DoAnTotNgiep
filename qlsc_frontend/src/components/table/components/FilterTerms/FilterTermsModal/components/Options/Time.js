import React, { useState } from "react";
import moment from 'moment';
import DatetimeRangePicker from 'components/react-bootstrap-datetimerangepicker';

function Time(props) {

  const initialState = {
    opens: "center",
    drops: "down",
    ranges: {
      "Hôm nay": [moment(), moment()],
      "Hôm qua": [
        moment().subtract(1, "days"),
        moment().subtract(1, "days"),
      ],
      "7 ngày trước": [moment().subtract(6, "days"), moment()],
      "30 ngày trước": [moment().subtract(29, "days"), moment()],
      "90 ngày trước": [moment().subtract(89, "days"), moment()],
    },
    locale: {
      applyLabel: "Áp dụng",
      cancelLabel: "Hủy",
      format: "DD/MM/YYYY",
      daysOfWeek: ["CN", "T2", "T3", "T4", "T5", "T6", "T7"],
      monthNames: [
        "tháng 1",
        "tháng 2",
        "tháng 3",
        "tháng 4",
        "tháng 5",
        "tháng 6",
        "tháng 7",
        "tháng 8",
        "tháng 9",
        "tháng 10",
        "tháng 11",
        "tháng 12",
      ],
      separator: " - ",
    },
  };

  const { time } = props;
  const [timeOptions, setTimeOptions] = useState(initialState); 

  const handleApply = (event, picker) => {
    const currentTime = {
      startTime: picker.startDate,
      endTime: picker.endDate,
    };
    setTimeOptions(currentTime);
  };

  const cancel = () => {
    setTime(defaultStage("time"));
  };

  const getTimeInfo = () => {
    if (time && time.startTime && time.endTime) {
      return `Từ ${moment(time.startTime).format("DD/MM/YYYY")} đến ${moment(
        time.endTime
      ).format("DD/MM/YYYY")}`;
    }
    return "Chọn thời gian bình luận";
  };

  return (
    <span className="filter-detail">
      <DatetimeRangePicker
        opens={timeOptions.opens}
        drops={timeOptions.drops}
        startDate={timeOptions.beginTime}
        endDate={timeOptions.endTime}
        onApply={handleApply}
        linkedCalendars={timeOptions.linkedCalendars}
        onCancel={cancel}
        ranges={timeOptions.ranges}
        locale={timeOptions.locale}
        alwaysShowCalendars
      >
        <button
          type="text"
          className="form-control dateRange"
          style={{ marginLeft: 10, width: 284, cursor: "pointer" }}
        >
          {getTimeInfo()}
        </button>
      </DatetimeRangePicker>
    </span>
  );
}

export default Time;
