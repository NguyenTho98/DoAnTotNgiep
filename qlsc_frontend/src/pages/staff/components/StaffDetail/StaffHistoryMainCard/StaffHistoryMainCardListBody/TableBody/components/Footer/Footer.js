import React from "react";
import { connect } from "react-redux";
import "../../styles/footer.scss";
import Pagination from "components/Pagination/Pagination";
function Footer(props) {
  const { staffHistoryMainCard, size, onChangeFilter, isEmpty , fetching} = props;
  const { currentPage, totalItems, totalPages, staffHistoryMainCards } = staffHistoryMainCard;

  const calculateBegin = () => {
    if (currentPage === 1) {
      return 1;
    }
    if (currentPage === totalPages) {
      return (size * (currentPage - 1) + 1);
    }
    return (size * (currentPage - 1)) + 1;
  };

  const calculateEnd = () => {
    if (totalPages === 1) {
      return totalItems;
    }
    if (currentPage === 1) {
      return (currentPage * size);
    }
    if (totalPages > currentPage) {
      return ((currentPage) * size);
    }
    if (currentPage === totalPages) {
      return totalItems;
    }
    return (currentPage * size) + (totalItems%currentPage);
  };

  if (fetching || isEmpty) return null;
  return (
    <div className="d-flex delivery-collations-footer">
      <div className="result-info">
        Hiển thị kết quả từ&nbsp;
        {calculateBegin()} -&nbsp;
        {calculateEnd()} trên tổng {totalItems}
      </div>
      <div className="margin-left-auto" />
      <div className="products-pagination">
      <Pagination
          totalPage={totalPages}
          page={currentPage}
          totalItem={totalItems}
          size={size}
          onChangeFilter={onChangeFilter}
        />
      </div>
    </div>
  );
}

export default connect(null, null)(Footer);

