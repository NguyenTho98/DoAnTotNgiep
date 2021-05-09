import React, { forwardRef } from "react";
import "../../styles/list.scss";
import Item from "../Item/Item";

const List = forwardRef((props, ref) => {
  const { customer } = props;
  const { customers } = customer;
  return (
    <div className="order-list-container">
      {customers.length &&
        customers.map((customer, index) => {
          return <Item key={customer.code} index={index} customer={customer} />;
        })}
    </div>
  );
});
export default List;
