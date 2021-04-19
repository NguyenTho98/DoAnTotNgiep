import React from "react";
import "./contentTable.scss";
import * as Icons from "../../Icons/Icons";
import Header from './Header/Header';
import BodyContent from './BodyContent/BodyContent';

function ContentTable(props) {

  const renderNoContent = () => {
    return (
      <div className="filter-null">
      <div className="row filter-null-icon">
        <Icons.messagesBigIcon />
      </div>
      <div className="row filter-null-text-three">
        Bài viết không có bình luận nào!
      </div>
    </div>
    )
  }

  return (
    <div id="wrapper-content-table">
      <div className="dashboard-body-content">
        <div className="content-container">
          <div className="customer-list-wrapper">
            <Header />
            <BodyContent />
          </div>
        </div>
      </div>
    </div>
  );
}

export default ContentTable;
