import { forEach } from 'lodash';
import React from 'react';
import { connect } from 'react-redux';
import Item from '../Item/Item';
function List(props) {



  // const removeOrderItem = (id) => {
  //   const arr = listOrderCollation.filter((item) => item.id !== id);
  //   updateOrderCollation(arr);
  // };
  // if (listOrderCollation.length === 0) {
  //   return (
  //     <div className="is-empty-order">
  //       <Icons.IconIsEmptyOrder />
  //       <p>Phiếu đối soát của bạn chưa có đơn hàng nào</p>
  //     </div>
  //   );
  // }
  return (
    <React.Fragment>
      <div className="list-order-selected">
        {/* {
        listOrderCollation.map((item, index) => (
          <Item
            key={index}
            item={item}
            onChangeItem={(id, name, value) => onChangeItem(id, name, value)}
            removeOrderItem={(id) => removeOrderItem(id)}
          />
        ))
      } */}
      <Item />
      <Item />
      <Item />
      <Item />
      </div>
    </React.Fragment>
  );
}

export default connect(null, null)(List);
