# Serverless tasks for Bamboo

Easily build and deploy serverless projects with additional tasks for Bamboo:

- Package serverless project and create artifact which can be deployed later
- Deploy serverless projects
- Destroy deployed project

You can choose from globally installed version of serverless framework (defined with custom executable capability) or
one defined via `packages.json` (you need to run npm install task before using serverless tasks in that case).

Bonus: 
 - Get URL of your freshly deployed API in build result variable `serverless.api.url`
 - Full support for Bamboo Specs
