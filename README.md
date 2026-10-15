# 教材管理システム（Coursebook MIS）

**Spring Boot 3 + Vue 3** によるフロントエンド・バックエンド分離型の大学教材管理システムです。教材の入庫から在庫管理、承認つき配布、学生からのフィードバックまで、一連の業務フローを備えています。

デモ：http://47.108.172.228:8082/（運用中）

## 機能一覧

**管理者・教務担当（教員）・学生** の 3 つのロールで構成されています。

| モジュール | 説明 |
|------|------|
| ユーザー管理 | 新規登録・ログイン（JWT 認証）・個人情報およびパスワード変更 |
| 教材情報管理 | 教材の基本情報とカテゴリの CRUD |
| 入庫管理 | 教材の入庫登録（在庫数を自動更新） |
| 在庫管理 | 教材ごとのリアルタイム在庫照会・調整 |
| 教材配布 | 教務担当がクラス単位で配布申請 → 管理者が承認（在庫を自動減算）→ 受取完了 |
| クラス管理 | クラス情報の管理 |
| お知らせ | 管理者が公開する全員向けのお知らせ |
| データ集計 | 入庫・配布・在庫数の統計および教材別在庫分布の円グラフ |
| フィードバック | 学生からの問い合わせ登録、教務担当による返信・処理 |

## 技術スタック（開発中に使用・学習した技術）

- **バックエンド**：Spring Boot 3.3、MyBatis、MySQL 8、JWT（hutool-jwt）、Lombok
- **フロントエンド**：Vue 3、Vite、Element Plus、ECharts、Axios
- **ツール**：Maven、npm

## プロジェクト構成

```
coursebook
├── springboot          # バックエンド（Spring Boot）
│   └── src/main/java/com/example
│       ├── controller  # API 層
│       ├── service     # ビジネスロジック層
│       ├── mapper      # MyBatis データアクセス層
│       ├── entity      # エンティティ
│       └── common      # 共通設定（JWT インターセプター、統一レスポンス、例外処理）
├── vue                 # フロントエンド（Vue 3）
│   └── src/views       # 画面（manager ディレクトリは管理画面）
└── coursebook.sql      # DB 初期化スクリプト（デモデータ付き）
```

## セットアップ手順

### 必要環境

- JDK 17+、Maven 3.8+
- Node.js 18+、MySQL 8.0

### 1. データベース初期化

DB `coursebook`（utf8mb4）を作成し、リポジトリ直下の `coursebook.sql` をインポートします。

### 2. バックエンド起動

`springboot/src/main/resources/application.yml` の DB 接続情報を環境に合わせて修正してから：

```bash
cd springboot
mvn spring-boot:run
```

バックエンドは `http://localhost:9090` で起動します。

### 3. フロントエンド起動

```bash
cd vue
npm install
npm run dev
```

`http://localhost:5173` にアクセスしてください。

### デモアカウント

| ロール | アカウント | パスワード |
|------|------|------|
| 管理者 | admin | admin |
| 教務担当 | lls | 123 |
| 学生 | 20201001 | 123 |

## デプロイ

1. フロントエンド：`vue/.env.production` の `VITE_BASE_URL` をサーバーアドレスに変更し、`npm run build` 後に `dist` を Nginx へ配置。`try_files $uri $uri/ /index.html;` の設定が必要（history ルーティングのリロード対策）。
2. バックエンド：`mvn package` で jar を生成し、`java -jar` で起動（BaoTa パネルの Java プロジェクトマネージャー等を利用）。ポート 9090 を開放。
3. DB：`coursebook.sql` をインポートし、`application.yml` の接続情報と `fileBaseUrl` をサーバー環境に合わせて変更。

## 今後の展望

- [ ] 在庫警告：在庫数がしきい値を下回ったら管理者へ通知
- [ ] 学期単位での教材・配布記録管理、学期ごとの対帳エクスポート
- [ ] 配布明細の学生単位での記録と受取確認

## 補足

本プロジェクトは大学の卒業研究として個人開発したものです（学習・就職活動向けのポートフォリオであり、データはデモ用です）。

---

[中文版はこちら](README.zh-CN.md)
