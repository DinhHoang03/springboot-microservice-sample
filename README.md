# Spring Boot Microservice Sample

Đây là dự án mẫu triển khai kiến trúc Microservice sử dụng **Spring Boot** và **Java**. Mục tiêu của dự án là minh họa cách các dịch vụ (services) độc lập được xây dựng, giao tiếp và hoạt động cùng nhau trong một hệ thống phân tán.

> **Tác giả:** Holy_Dev

## 1. 💻 Công nghệ (Technology Stack)

* **Ngôn ngữ:** Java
* **Framework:** Spring Boot
* **Giao tiếp (Communication):**
    * REST API (đồng bộ)
    * Apache Kafka (bất đồng bộ, event-driven)
* **Cơ sở hạ tầng:** Docker (dựa trên sự tồn tại của `kafka-docker`)

## 2. 🏗️ Kiến trúc (Architecture)

Dự án tuân thủ mô hình Microservice, chia nhỏ ứng dụng thành các thành phần độc lập.

### 2.1. Dịch vụ Nghiệp vụ (Core Services)

Đây là các dịch vụ chứa logic nghiệp vụ cốt lõi của ứng dụng:

* `user-service`: Chịu trách nhiệm quản lý thông tin và các nghiệp vụ liên quan đến Người dùng (User).
* `order-service`: Chịu trách nhiệm xử lý các nghiệp vụ liên quan đến Đơn hàng (Order), ví dụ: tạo đơn, theo dõi trạng thái.

### 2.2. Dịch vụ Cơ sở hạ tầng (Infrastructure Services)

Đây là các dịch vụ hỗ trợ việc vận hành, giao tiếp, và giám sát toàn bộ hệ thống:

| Tên Dịch vụ | Vai trò | Mô tả chi tiết |
| :--- | :--- | :--- |
| `discovery-server` | **Service Discovery** | Đóng vai trò là trung tâm đăng ký (Registry) cho tất cả Microservice. Giúp các dịch vụ tự động tìm thấy địa chỉ của nhau mà không cần hard-code IP/port. |
| `api-gateway` | **API Gateway** | Điểm truy cập (Entry point) duy nhất cho các ứng dụng khách (clients) bên ngoài. Chịu trách nhiệm định tuyến yêu cầu, cân bằng tải, và có thể xử lý các tác vụ chung như xác thực/ủy quyền. |
| `kafka-docker` | **Message Broker** | Cung cấp một phiên bản Apache Kafka (chạy trên Docker) để thiết lập giao tiếp bất đồng bộ (asynchronous) và xử lý sự kiện (event-driven) giữa các dịch vụ. |
| `notification-service` | **Notification Service** | Một dịch vụ lắng nghe các sự kiện được đẩy vào Kafka (ví dụ: "đơn hàng đã tạo" từ `order-service`) và thực hiện hành động thông báo (ví dụ: gửi email, SMS). |

## 3. 🚀 Bắt đầu (Getting Started)

Phần này mô tả các bước để cài đặt và chạy dự án trên máy cục bộ.

### Yêu cầu (Prerequisites)

* Java JDK (phiên bản 17 hoặc cao hơn)
* Maven hoặc Gradle
* Docker & Docker Compose

### Cài đặt (Installation)

1.  Clone kho lưu trữ:
    ```bash
    git clone [https://github.com/DinhHoang03/springboot-microservice-sample.git](https://github.com/DinhHoang03/springboot-microservice-sample.git)
    cd springboot-microservice-sample
    ```

2.  Khởi chạy các dịch vụ cơ sở hạ tầng (như Kafka):
    ```bash
    cd kafka-docker
    docker-compose up -d
    ```

3.  Build và chạy các dịch vụ (ví dụ với Maven):
    *(Lặp lại cho từng service: `discovery-server`, `api-gateway`, `user-service`, v.v.)*
    ```bash
    cd user-service
    mvn spring-boot:run
    ```

## Lời cảm ơn (Acknowledgements)

Dự án này được học hỏi và xây dựng dựa trên series hướng dẫn chi tiết về Microservice của **Holy_dev**. Xin gửi lời cảm ơn đến tác giả vì nội dung chia sẻ giá trị.

* **Link series:** [Spring Boot Microservices – Học từ số 0](https://www.youtube.com/playlist?list=PL3ZNQ7Ti-wWo5FoHyBtDfzX7DE15UPy4N)
* **Link youtube chủ kênh:** [Holy_Dev](https://www.youtube.com/@dev-maniac2349/featured)
