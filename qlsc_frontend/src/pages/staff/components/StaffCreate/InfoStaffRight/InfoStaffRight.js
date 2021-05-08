/* eslint-disable no-shadow */
import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import "./styles.scss";
function InfoStaffRight(props) {
  const {} = props;
  useEffect(() => {}, []);
  return (
    <div className="info-staff-right">
      <div className="card info-staff-right-01">
        <div className="title">Tài khoản & mật khẩu</div>
        <div className="content">
          <div className="field form-group">
            <span style={{ color: 'red', marginRight: '4px' }}>*</span>
            <label className="control-label">Email nhân viên</label>
            <div className="controls">
              <input
                className="input"
                data-tip=""
                data-for="_extends_popup_error"
                name="name"
                placeholder="Nhập tên sản phẩm"
              />
            </div>
          </div>
          <div className="field form-group">
            <span style={{ color: 'red', marginRight: '4px' }}>*</span>
            <label className="control-label">Mật khẩu </label>
            <div className="controls">
              <input
                className="input"
                data-tip=""
                data-for="_extends_popup_error"
                name="name"
                placeholder="Nhập tên sản phẩm"
              />
            </div>
          </div>
          <div className="field form-group">
            <span style={{ color: 'red', marginRight: '4px' }}>*</span>
            <label className="control-label">Vai trò nhân viên</label>
            <div className="controls">
              <input
                className="input"
                data-tip=""
                data-for="_extends_popup_error"
                name="name"
                placeholder="Nhập tên sản phẩm"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
InfoStaffRight.defaultProps = {};

export default React.memo(connect(null, null)(InfoStaffRight));
