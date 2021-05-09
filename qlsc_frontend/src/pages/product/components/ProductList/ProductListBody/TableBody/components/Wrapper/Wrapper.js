import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import List from "../List/List";
import Header from "../Header/Header";
import Footer from "../Footer/Footer";
import "../../styles/wrapper.scss";
import * as Icons from "pages/maintenancecard/commons/Icons";

function Wrapper(props) {
  const { product, onChangeFilter } = props;
  const [selectedIds, setSelectedIds] = useState([]);
  const listRef = React.useRef();

  const onCheckBoxClick = (id) => {
    setSelectedIds([]);
  };

  const resetSelected = () => {
    setSelectedIds([]);
  };

  const onClick = () => {
    listRef && listRef.current.onCheckAll();
  };

  const onCheckBoxListClick = (ids) => {
    setSelectedIds([]);
  };

  const renderCheckInfo = () => {
    return (
      <div className="count-check">
        <span className="details">
          Đã chọn ({selectedIds.length} sản phẩm dịch vụ)
        </span>
      </div>
    );
  };

  const child = renderCheckInfo();
  const isEmpty = product && !product.totalItem;
  if (isEmpty) {
    return (
      <div className="product-list-wrapper">
        <div id="product-filter-empty-wrapper">
          <div id="product-filter-empty-text">Không có sản phẩm dịch vụ</div>
          <div id="product-filter-empty-icon">
            <Icons.OrderCollationFilterEmpty />
          </div>
        </div>
      </div>
    );
  }
  return (
    <React.Fragment>
      <div className="product-list-wrapper">
        <Header onClick={onClick} checked={false} minus={false} child={child} />
        <List product={product} />
        <Footer
          onChangeFilter={onChangeFilter}
          product={product}
          resetSelected={resetSelected}
          isEmpty={isEmpty}
        />
      </div>
    </React.Fragment>
  );
}
Wrapper.defaultProps = {
  selectedIds: [],
  product: {},
};

const mapStateToProps = (state) => {
  const { product } = state;
  return {
    product,
  };
};

export default withRouter(connect(mapStateToProps, null)(Wrapper));
