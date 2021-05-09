import React, { forwardRef } from "react";
import "../../styles/list.scss";
import Item from "../Item/Item";

const List = forwardRef((props, ref) => {
  const { product } = props;
  const { productSerives } = product;
  return (
    <div className="order-list-container">
      {productSerives.length &&
        productSerives.map((productService, index) => {
          return (
            <Item
              key={productService.code}
              index={index}
              productService={productService}
            />
          );
        })}
    </div>
  );
});
export default List;
