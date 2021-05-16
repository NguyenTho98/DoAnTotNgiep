import React, { useState } from "react";
import Pagination from "components/Pagination/Pagination";
import { connect } from "react-redux";
import "../../styles/footer.scss";

function Footer(props) {
  const { product, onChangeFilter, isEmpty , fetching} = props;
  const { currentPage, totalItem, totalPage, productSerives } = product;
  const [size, setSize] = useState(10);

  const calculateBegin = () => {
    if (currentPage === 1) {
      return 1;
    }
    if (currentPage === totalPage) {
      return (size * (currentPage - 1) + productSerives.length);
    }
    return (size * currentPage) + 1;
  };

  const calculateEnd = () => {
    if (totalPage === 1 && totalItem > 0) {
      return totalItem;
    }
    if (currentPage === 1) {
      return (currentPage * size);
    }
    if (totalPage > currentPage) {
      return ((currentPage + 1) * size);
    }
    if (currentPage === totalPage) {
      return totalItem;
    }
    return (currentPage * size) + (totalItem%currentPage);
  };
  if (fetching || isEmpty) return null;
  return (
    <div className="d-flex delivery-collations-footer">
      <div className="result-info">
        Hiển thị kết quả từ&nbsp;
        {calculateBegin()} -&nbsp;
        {calculateEnd()} trên tổng {totalItem}
      </div>
      <div className="margin-left-auto" />
      <div className="products-pagination">
        <Pagination
          totalPage={totalPage}
          page={currentPage}
          totalItem={totalItem}
          size={size}
          onChangeFilter={onChangeFilter}
        />
      </div>
    </div>
  );
}

Footer.defaultProps = {
  size: 10,
};

export default connect(null, null)(Footer);
