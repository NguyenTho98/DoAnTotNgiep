import React from "react";
import * as Icons from "../../Icons/Icons";
import "./paginationBase.scss";

function PaginationBase(props) {
  const data = require("./data.json");
  const { comments } = data;
//   body: "laudantium enim quasi est quidem magnam voluptate ipsam eos↵tempora quo necessitatibus↵dolor quam autem quasi↵reiciendis et nam sapiente accusantium";
//   email: "Eliseo@gardner.biz";
//   id: 1;
//   name: "id labore ex et quam laborum";
//   postId: 1;

  const renderListPage = (data) => {
    let listPage = [];
    let temp = 0
    data.forEach((item, index) => {
        if (temp === 0) {
            temp = 1;
            listPage.push(0);
        }
        if (index - temp === 24) {
            listPage.push(listPage.length + 1);
            temp = index;
        }
    });
    return listPage;
  }
  const page = 1;
  const size = 24;
  const total = comments.length;
  const listPage = renderListPage(comments);
  const maxPage = Math.ceil(total / size);
  return (
    <div id="wrapper-pagination">
      <div id="pagination-count">
        {`Hiển thị kết quả từ ${(page - 1) * size + 1} - ${
          (page - 1) * size + comments.length
        } trên tổng ${total}`}
      </div>
      {total > 20 ? (
        <div id="pagination-size">
          <div className="pagination-size-text">Hiển thị</div>
          <div className="pagination-size-option">
            <button
              type="button"
              className="dropdown-toggle pagination-size-button"
              data-toggle="dropdown"
            >
              {size}
              <Icons.Arrow />
            </button>
            <div className="dropdown-menu pagination-size-menu">
              <a
                className="dropdown-item pagination-size-item"
                onClick={() => this.selectSize(20)}
              >
                20
              </a>
              <a
                className="dropdown-item pagination-size-item"
                onClick={() => this.selectSize(50)}
              >
                50
              </a>
              <a
                className="dropdown-item pagination-size-item"
                onClick={() => this.selectSize(100)}
              >
                100
              </a>
            </div>
          </div>
          <div className="pagination-size-text">kết quả</div>
        </div>
      ) : null}
      {total > size ? (
        <div id="pagination-page">
          <ul className="ul-pagination">
            {listPage.map((index) => {
              switch (index) {
                case 0:
                  return (
                    <li
                      key={index}
                      className={page === 1 ? "disabled" : ""}
                      onClick={() => this.selectPage(1)}
                    >
                      <a href="#">
                        <span aria-hidden="true">&larr;</span> Trang đầu
                      </a>
                    </li>
                  );
                // case maxPage + 1:
                case maxPage:
                  return (
                    <li
                      key={index}
                      className={page === maxPage ? "disabled" : ""}
                      onClick={() => this.selectPage(maxPage)}
                    >
                      <a>
                        Trang cuối <span aria-hidden="false">&rarr;</span>
                      </a>
                    </li>
                  );
                default:
                  return (
                    <li
                      key={index}
                      className={index === page ? "active" : ""}
                      onClick={() => this.selectPage(index)}
                    >
                      <a>{index}</a>
                    </li>
                  );
              }
            })}
          </ul>
        </div>
      ) : null}
    </div>
  );
}

export default PaginationBase;
