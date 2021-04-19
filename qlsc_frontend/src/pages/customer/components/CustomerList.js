import React, { Component } from "react";
import { connect } from "react-redux";
import { getListCustomer } from "../actions/customerAction";
import TableBase from "../table/components/TableBase/TableBase";

class CustomerList extends Component {
  componentDidMount() {
    this.props.onGetCustomer();
  }

  render() {
    return (
      <div>
        <TableBase customer={this.props.customer} />
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  const { customer } = state;
  return {
    customer,
  };
};

const mapDispatchToProps = (dispatch) => ({
  onGetCustomer: (key, page, size, name, order) =>
    dispatch(getListCustomer(key, page, size, name, order)),
});

export default connect(mapStateToProps, mapDispatchToProps)(CustomerList);
