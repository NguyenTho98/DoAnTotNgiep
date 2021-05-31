import React from "react";

function NotFoundComponent() {
  return (
    <div style={{ background: "#ffffff", height: 'calc((100vh - 100px))' }}>
      {/* <div style={{ color: "#0084ff", textAlign: "center", fontSize: 15 }}>
        Không tìm thấy trang!
      </div> */}
      <div style={{ textAlign: "center", margin: 20 }}>
        <img src={`/images/NoDomain.png`} alt="not-found" />
      </div>
      {/* <div style={{ textAlign: "center", fontSize: 15 }}>
        Chúng tôi rất tiếc vì không tìm thấy trang web bạn truy cập.
      </div> */}
    </div>
  );
}

export default NotFoundComponent;
