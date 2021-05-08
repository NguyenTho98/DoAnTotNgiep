import React from "react";
import "./styles.scss";
import { useHistory } from 'react-router';
import * as Icons from 'pages/maintenancecard/commons/Icons';
import { connect } from "react-redux";
import pushstate from '../../../../../utils/pushstate';
function StaffListHeader(props) {
  const history = useHistory();
  const onClick = () => {
    pushstate(history, `/staff/create`);
  };
  return (
    <div className="tilte-action">
      <div className="d-flex list-header">
        <div className="header-title">
          <div style={{ fontSize: "22px" }}>Danh sách nhân viên</div>
        </div>
        <div className="header-action">
          <button
            className="d-flex align-items-center justify-content-between btn btn-create"
            type="button"
            onClick={onClick}
          >
            <span
              className="d-flex align-items-center justify-content-center"
              style={{ marginLeft: 10 }}
            >
              <Icons.Create />
            </span>
            Tạo mới nhân viên
          </button>
        </div>
      </div>
    </div>
  );
}
StaffListHeader.defaultProps = {};

export default React.memo(connect(null, null)(StaffListHeader));
