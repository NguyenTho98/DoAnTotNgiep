import React from 'react';
import './styles.scss';

function InfoMainCard(props) {
  return (
    <div className="info-main-card-warpper">
      <div className="title">
        Thông tin phiếu sửa chữa
      </div>
      <div className="d-flex field">
        <div className="label">Mã phiếu</div>
        <input
          className="customer-name"
          type="text"
          name="phone"
          onChange={(e) => this.onChangePhone(e.target.value)}
        />
      </div>
      <div className="d-flex field">
        <div className="label">Biển số xe</div>
        <input
          className="customer-name"
          type="text"
          name="phone"
          onChange={(e) => this.onChangePhone(e.target.value)}
        />
      </div>
      <div className="d-flex field">
        <div className="label">Nhân viên sửa chữa</div>
        <input
          className="customer-name"
          type="text"
          name="phone"
          onChange={(e) => this.onChangePhone(e.target.value)}
        />
      </div>
      <div className="d-flex field">
        <div className="label">Màu xe</div>
        <input
          className="customer-name"
          type="text"
          name="phone"
          onChange={(e) => this.onChangePhone(e.target.value)}
        />
      </div>
      <div className="d-flex field">
        <div className="label">Loại xe</div>
        <input
          className="customer-name"
          type="text"
          name="phone"
          onChange={(e) => this.onChangePhone(e.target.value)}
        />
      </div>

      <div className="d-flex field">
        <div className="label">Nhân viên đồi phối</div>
        <input
          className="customer-name"
          type="text"
          name="phone"
          onChange={(e) => this.onChangePhone(e.target.value)}
        />
      </div>
      <div className="d-flex field">
        <div className="label">Ngày trả xe</div>
        <input
          className="customer-name"
          type="text"
          name="phone"
          onChange={(e) => this.onChangePhone(e.target.value)}
        />
      </div>
      <div className="d-flex field">
        <div className="label">Ngày dự kiến trả xe</div>
        <input
          className="customer-name"
          type="text"
          name="phone"
          onChange={(e) => this.onChangePhone(e.target.value)}
        />
      </div>
      <div className="d-flex field">
        <div className="label">Mô tả</div>
        <input
          className="customer-name"
          type="text"
          name="phone"
          onChange={(e) => this.onChangePhone(e.target.value)}
        />
      </div>
    </div>
  );
}

export default InfoMainCard;
