import React from "react";
import TodayItem from "../components/TodayItem";
import * as ReportIcons from './Icons';
import "./styles.scss";
function ReportRight(props) {
  const { data } = props;
  const listItem = [
    {
      id: 0,
      backgroundColorCustom: '#effbf8',
      name: 'Tổng số phiếu',
      value: data.totalMaintenanceCard,
      icon: <ReportIcons.dollarColorIcon />,
    },
    {
      id: 1,
      backgroundColorCustom: '#fef7ff',
      name: 'Tổng số phiếu thành công',
      value: data.totalMaintenanceCardSuccess,
      icon: <ReportIcons.rateColorIcon />,
    },
    {
      id: 2,
      backgroundColorCustom: '#eff9fc',
      name: 'Phiếu thành công đã thanh toán',
      value: data.totalMaintenanceCardScPayed,
      icon: <ReportIcons.dollarColorIcon />,
    },
    {
      id: 3,
      backgroundColorCustom: '#fef5f5',
      name: 'Phiếu thành công chưa thanh toán',
      value: data.totalMaintenanceCardScNotPay,
      icon: <ReportIcons.rateColorIcon />,
    },
  ];
  return (
    <React.Fragment>
      <div className="report-today-component report-border ">
        <div className="report-title">Báo cáo nhanh Hôm nay</div>
        <div className="content">
          {listItem.map((item, index) => {
            return (
              <TodayItem data={item} key={index} />
            );
          })}
        </div>
      </div>
    </React.Fragment>
  );
}

ReportRight.defaultProps = {
  data: {},
};

export default ReportRight;
