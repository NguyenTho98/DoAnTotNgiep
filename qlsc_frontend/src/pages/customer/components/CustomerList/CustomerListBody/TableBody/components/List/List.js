
import React, { forwardRef } from 'react';
import '../../styles/list.scss';
import Item from '../Item/Item';
import Guard from 'components/Guard/Guard'
const List = forwardRef((props, ) => {
  const { customer, fetching, isEmpty} = props;
  const { customers } = customer;
  if (isEmpty) return '';
  if (fetching) {
    return (
      <Guard />
    );
  }
  return (
    <div className="order-list-container">
      {customers.length && customers.map((customer) => {
        return <Item customer={customer} />
      })}
    </div>
  );
});
export default List;
