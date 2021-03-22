import React from "react";
import "./productList.scss";
import { toggleModalAction } from "components/modal/modalActions";
import { connect } from "react-redux";
import TableBase from "../../../components/table/components/TableBase/TableBase";

function ProductList(props) {
  return (
    <div className="wrapper-table" id="wrapper-product-list">
      <div className="content-list">
        <TableBase />
      </div>
    </div>
  );
}

const mapStateToProps = (state) => {
  const {
    modal: { createProductModal },
  } = state;
  return {
    createProductModal,
  };
};
const mapDispatchToProps = (dispatch) => ({
  toggleModalAction: (modalName, data) =>
    dispatch(toggleModalAction(modalName, data)),
});

export default React.memo(
  connect(mapStateToProps, mapDispatchToProps)(ProductList)
);
