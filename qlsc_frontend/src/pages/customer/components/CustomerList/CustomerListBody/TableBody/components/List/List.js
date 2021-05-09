
import React, { forwardRef } from 'react';
import '../../styles/list.scss';
import Item from '../Item/Item';

const List = forwardRef((props, ) => {
  const { customer } = props;
  const { customers } = customer;
  // if (fetching) {
  //   return (
  //     <Guard />
  //   );
  // }
  return (
    <div className="order-list-container">
      {customers.length && customers.map((customer) => {
        return <Item customer={customer} />
      })}
    </div>
  );
});
export default List;
