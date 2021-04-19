import React, { Component } from "react";
import SelectDistricts from './SelectAddress/SelectDistricts';
import "../styles/addCustomer.scss";

export class AddCustomer extends Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      nameReceiver: null,
      phone: null,
      address: null,
      city: null,
      ward: null,
      illusory: null,
      isInvalidPhone: false,
      isInvalidAddress: false,
      isAddPosAddress: false,
      isAddSocialAddress: false,
      showPopupPosAddress: false,
      selectWardClicked: false,
      selectDistrictClicked: false,
      country_id: 0,
      city_id: 0,
      district_id: 0,
      ward_id: 0,
      phoneReceiver: null,
      isEditPhone: false,
      isEditName: false,
    };
  }
  
  header() {
    return (
      <div className="ui-title-bar-container">
        <div className="ui-title-bar">
          <div className="ui-title-bar__navigation">Danh sách khách hàng</div>
          <div className="ui-title-bar__main-group">
            <h1 className="ui-title-bar__title">Thêm mới khách hàng</h1>
          </div>
        </div>
      </div>
    );
  }

  customerMainInfo() {
    return (
      <div className="customer-create-left">
        <div className="page-info">
          <div className="page-info-body">
            {/* customer-name */}
            <div className="col-12 field p-0">
              <div className="label mb-2 label-required">Tên khách hàng</div>
              <input className="customer-name" type="text" name="name" />
            </div>
            {/* customer-code */}
            <div className="col-6 field pl-0">
              <div className="label mb-2">Mã khách hàng</div>
              <input className="customer-name" type="text" name="code" />
            </div>
            {/* customer-group */}
            <div className="col-6 field pr-0">
              <div className="label mb-2">Nhóm khách hàng</div>
              <input className="customer-name" type="text" name="group" />
            </div>
            {/* customer-phone */}
            <div className="col-6 field pl-0">
              <div className="label mb-2">Số điện thoại</div>
              <input className="customer-name" type="text" name="phone" />
            </div>
            {/* customer-email */}
            <div className="col-6 field pr-0">
              <div className="label mb-2">Email</div>
              <input className="customer-name" type="text" name="email" />
            </div>
          </div>
        </div>

        <div className="page-info">
          <div className="page-info-title">Thông tin địa chỉ</div>
          <div className="page-info-body">
            {/* customer-address */}
            <div className="col-6 field pl-0">
              <div className="label mb-2">Địa chỉ</div>
              <input className="customer-name" type="text" name="address" />
            </div>
            {/* customer-cityDistrict */}
            <div className="col-6 field pr-0">
              <div className="label mb-2">Khu vực</div>
              <div
                id="editting-customer-address-district"
                ref={(e) => {
                  this.selectDistrict = e;
                }}
                className="customer-name"
              >
                <SelectDistricts
                  city={this.state.city}
                  illusory={this.state.illusory}
                  onSelect={(e) => this.onChangeSelectDistrict(e.target.value)}
                  ref={this.setCityRef}
                />
              </div>
            </div>
            {/*  */}
            <div className="col-6 field pl-0" />
            {/* customer-ward */}
            <div className="col-6 field pr-0">
              <div className="label mb-2">Phường xã</div>
              <input className="customer-name" type="text" name="ward" />
            </div>
          </div>
        </div>
      </div>
    );
  }

  customerDifferentInfo() {
    return (
      <div className="customer-create-right">
        <div className="page-info">
          <div className="page-info-title">Thông tin khác</div>
          <div className="page-info-body">
            {/* choose-customer */}
            <div className="col-12 field p-0">
              <div class="label mb-2">Nhân viên phụ trách</div>
              <div className="select__wrapper " id="search-account-wrap">
                {/* <a
                  class="customer-select select--a select-suggest"
                  id="search-account"
                  href="javascript:"
                  data-original-title=""
                  title=""
                >
                  Chọn nhân viên
                </a> */}
                <div class="filter-body--suggest"></div>
              </div>
            </div>
            {/* customer-description */}
            <div className="page-info-item col-12 no-padding mb-4">
              <div className="page-info-item-title">Mô tả</div>
              <textarea
                bind="description"
                className="customer-textbox"
                style={{ height: "60px" }}
              ></textarea>
            </div>
            {/* customer-description */}
            <div className="page-info-item col-12 no-padding mb-4">
              <div className="page-info-item-title">TAG</div>
              <textarea
                bind="description"
                className="customer-textbox"
                style={{ height: "60px" }}
              ></textarea>
            </div>
          </div>
        </div>
      </div>
    );
  }

  customnerBottom() {
    return (
      <div className="ui-title-bottom-bar">
        <a class="btn btn-default right btn-create-customer">Lưu</a>
        <a class="btn btn-blank right mr-3">Huỷ</a>
      </div>
    );
  }
  render() {
    const headerElm = this.header();
    const createLeftElm = this.customerMainInfo();
    const createRightElm = this.customerDifferentInfo();
    const saveAndCancelElm = this.customnerBottom();
    return (
      <div className="wrapper-add-customer">
        {headerElm}
        <div className="customer-container container">
          {createLeftElm}
          {createRightElm}
        </div>
        {saveAndCancelElm}
      </div>
    );
  }
}

export default AddCustomer;
