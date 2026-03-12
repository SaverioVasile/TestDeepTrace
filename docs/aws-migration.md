# AWS migration notes (after local docker-compose)

## Proposed free-tier-friendly setup
- **Backend**: AWS Elastic Beanstalk (single instance) or ECS Fargate (smallest task)
- **Database**: PostgreSQL on Amazon RDS free tier (if available in your region)
- **Email**: Amazon SES SMTP credentials
- **Frontend**: S3 + CloudFront for static hosting, or keep it containerized in ECS/Beanstalk

## Suggested path
1. Keep using local docker-compose for functional validation.
2. Provision RDS PostgreSQL and migrate backend env vars.
3. Verify SES domain/email identity and move `APP_MAIL_ENABLED=true`.
4. Deploy backend first, then deploy frontend with `VITE_API_BASE_URL` set to backend public URL.
5. Enable HTTPS and lock CORS to production domain.

## Minimal environment variables in AWS
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `APP_MAIL_ENABLED=true`
- `APP_MAIL_FROM`
- `SPRING_MAIL_HOST`
- `SPRING_MAIL_PORT`
- `SPRING_MAIL_USERNAME`
- `SPRING_MAIL_PASSWORD`
- `APP_CORS_ALLOWED_ORIGINS`

