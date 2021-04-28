/* eslint-disable jsx-a11y/click-events-have-key-events */
import React, { useState } from 'react';
import * as Icons from 'components/sapo/deliveryCollation/commons/Icons';
import { connect } from 'react-redux';
import Line from './Line/Line';
import InputTag from '../../../../productListing/CreateProductListing/CreateWrapper/ProductSpecify/HashTagGenerator/inputTag/InputTag';

let inputRef;

function DeliveryCollationInfo(props) {
  const noteTmp = <textarea placeholder="Ghi chú" />;

  const tagTmp = <textarea placeholder="Tag" />;

  const {
    arrLocations, locationId, note, tags, onChangeLine, onSelectLoaction, store,
    addTag,
    deleteTag,
    handleClose,
    onChange,
    listOrderCollation,
  } = props;
  const [focus, setFocus] = useState(false);
  const renderLocationLabel = () => {
    if (!locationId && arrLocations.length > 0) {
      return arrLocations[0].label;
    }
    const selected = arrLocations.find((item) => item.id === locationId);
    if (selected) {
      return selected.label;
    }
    return 'Không có chi nhánh nào';
  };
  const renderStore = () => {
    if (store === 1) {
      return (
        <div className="content d-flex">
          <div className="logo">
            <Icons.CreateShopee />
          </div>
          <div className="info">
            <h6 className="name">Sàn Shopee</h6>
            <p className="phone">1900 1221</p>
          </div>
        </div>
      );
    }
    if (store === 2) {
      return (
        <div className="content d-flex">
          <div className="logo">
            <Icons.CreateLazada />
          </div>
          <div className="info">
            <h6 className="name">Sàn Lazada</h6>
            <p className="phone">1900 1007</p>
          </div>
        </div>
      );
    }
    if (store === 3) {
      return (
        <div className="content d-flex">
          <div className="logo">
            <Icons.CreateSendo />
          </div>
          <div className="info">
            <h6 className="name">Sàn Sendo</h6>
            <p className="phone">1900 6771</p>
          </div>
        </div>
      );
    }
    if (store === 4) {
      return (
        <div className="content d-flex">
          <div className="logo">
            <Icons.CreateTiki />
          </div>
          <div className="info">
            <h6 className="name">Sàn Tiki</h6>
            <p className="phone">1900 6034</p>
          </div>
        </div>
      );
    }
  };

  const setChildRef = (e) => {
    inputRef = e;
  };

  const handleClick = () => {
    if (inputRef) {
      inputRef.focus();
      setFocus(true);
    }
  };

  return (
    <div className="align-items-center justify-content-between delivery-collation-info-wrapper">
      <div className="info-container bottom">
        <div className="title">
          Thông tin phiếu
        </div>
        <div className="content">
          <div className="row">
            <div className="col-6">
              <div className="d-flex info-store">
                <div className="title-option">
                    Sàn TMĐT
                </div>
                <div className="info-store-item">
                  {renderStore()}
                </div>
              </div>
              <Line
                name="Ghi chú"
                onChangeLine={(id, e) => onChangeLine(id, e)}
                valueHtml={noteTmp}
                value={note}
                id="note"
              />
            </div>
            <div className="col-6">
              <div className="filter-delivery-collations-option">
                <div className="title-option">
                  Chi nhánh
                </div>
                <button
                  type="button"
                  className="dropdown-toggle delivery-collations-dropdown-button"
                  data-toggle="dropdown"
                >
                  {renderLocationLabel()}
                  <Icons.Arrow />
                </button>
                <div className="dropdown-menu delivery-collations-dropdown-menu">
                  {arrLocations.map((item, index) => {
                    return (
                      <a
                        className="dropdown-item delivery-collations-dropdown-item"
                        key={index}
                        href
                        onClick={() => onSelectLoaction(item.id)}
                      >
                        {item.label}
                      </a>

                    );
                  })}
                </div>
              </div>
              <div className="delivery-collations-tags">
                <div className="title-option">
                Tag
                </div>
                <div className={`${focus ? 'input-tags input-tags-focus' : 'input-tags'}`} onClick={handleClick}>
                  <InputTag
                    tags={tags}
                    addTag={addTag}
                    onDelete={deleteTag}
                    placeHolder="Nhập tag và bấm Enter"
                    onFocus={() => { }}
                    onBlur={() => setFocus(false)}
                    onChange={onChange}
                    handleClose={handleClose}
                    allowAdd
                    isFocus
                    focus={focus}
                    showSearch
                    preventTooltip
                    createdMessage="Hash tag này đã được tạo"
                    childRef={setChildRef}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
const mapStateToProps = (state) => {
  const {
    locations
  } = state;
  return {
    locations
  };
};
export default connect(mapStateToProps, null)(DeliveryCollationInfo);
