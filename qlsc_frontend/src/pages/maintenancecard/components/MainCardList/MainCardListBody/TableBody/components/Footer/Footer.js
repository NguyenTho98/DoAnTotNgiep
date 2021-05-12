import React, { useState } from "react";
import { connect } from "react-redux";
import "../../styles/footer.scss";
import Pagination from "../Pagination/Pagination";

function Footer(props) {
  const { mainCards, fetchMainCard, isEmpty , fetching} = props;
  const { currentPage, totalItems, totalPages, mainCardList } = mainCards;

  const calculateBegin = () => {
    if (currentPage === 1) {
      return 1;
    }
    if (currentPage === totalPages) {
      return (totalItems * (currentPage - 1) + mainCardList.length);
    }
    return (totalItems * currentPage) + 1;
  };
  const onChangePage = (id) => {
    fetchMainCard(null, id);
  };
  const calculateEnd = () => {
    if (totalPages === 1) {
      return totalItems + 1;
    }
    if (currentPage === 1) {
      return (currentPage * totalItems);
    }
    if (totalPages > currentPage) {
      return ((currentPage + 1) * totalItems);
    }
    if (currentPage === totalPages) {
      return totalItems + 1;
    }
    return (currentPage * totalItems) + (totalItems%currentPage);
  };
  if (fetching || isEmpty) return null;
  return (
    <div className="d-flex delivery-collations-footer">
      <div className="result-info">
        Hiển thị kết quả từ&nbsp;
        {calculateBegin()} -&nbsp;
        {calculateEnd()} trên tổng {totalItems + 1}
      </div>
      <div className="margin-left-auto" />
      <div className="products-pagination">
      <Pagination
          total={totalPages}
          page={currentPage}
          size={totalItems}
          onClick={onChangePage}
        />
      </div>
    </div>
  );
}

Footer.defaultProps = {
  size: 10,
};

export default connect(null, null)(Footer);
