import React from "react";
import "./contentTable.scss";
import * as Icons from "../../Icons/Icons";

function ContentTable(props) {
  return (
    <div id="wrapper-content-table">
      <div className="filter-null">
        <div className="row filter-null-icon">
          <Icons.messagesBigIcon />
        </div>
        <div className="row filter-null-text-three">
          Bài viết không có bình luận nào!
        </div>
      </div>
    </div>
  );
}

export default ContentTable;
