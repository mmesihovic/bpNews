const Koa = require('koa');
const bodyParser = require('koa-bodyparser');
const compression = require('koa-compress');
const router = require('koa-router');
const kstatic = require('koa-static');
const convert = require('koa-convert');

const join = require('path').join;
const config = {
  name: 'cinemadb',
  db: 'cinemadb',
  username: 'postgres',
  password: 'kasika2366',
  dialect: 'postgresql',
  host: '127.0.0.1',
  port: 5432,
  pool: {
    maxConnections: 10,
    minConnections: 0,
    maxIdleTime: 30000
  }
};
 
const orm = require('koa-orm')(config);
const app = new Koa();
const koaRouter= new router();


app.use(orm.middleware);

koaRouter.get('/api/cinemas', ctx => {
  
  ctx.set('Content-Type', 'application/json');
  ctx.set('Access-Control-Allow-Origin', '*');
  return ctx.body =  ctx.orm('cinemadb').sql.select().from('public."Cinema"').query();
});

koaRouter.get('/api/movies', ctx => {
  ctx.set('Content-Type', 'application/json');
  ctx.set('Access-Control-Allow-Origin', '*');
  return ctx.body =  ctx.orm('cinemadb').sql.select().from('public."Movie"').query();
  });

koaRouter.get('/api/cinema-movies', ctx => {
  ctx.set('Content-Type', 'application/json');
  ctx.set('Access-Control-Allow-Origin', '*');
  return ctx.body =  ctx.orm('cinemadb').sql.select().from('public."CinemaMovie"').query();
});

koaRouter.get('/api/cinema-movies/:id', ctx=>{
  ctx.set('Content-Type', 'application/json');
  ctx.set('Access-Control-Allow-Origin', '*');
  return ctx.body= ctx.orm('cinemadb').sql.select().from('public."CinemaMovie"').where('"CinemaId" ='+ ctx.params.id).query();
});
/*koaRouter.get('/api/movie/:id', ctx=>{
  ctx.set('Content-Type', 'application/json');
  ctx.set('Access-Control-Allow-Origin', '*');
  return ctx.body= ctx.orm('cinemadb').sql.select().from('public."Movie"').where('"Id" ='+ ctx.params.id).query();
});*/

koaRouter.get('/api/cinema-movies/:id',function*(ctx, next){
  ctx.set('Content-Type', 'application/json');
  ctx.set('Access-Control-Allow-Origin', '*');
  yield next();
  return ctx.body= ctx.orm('cinemadb').sql.select().from('public."CinemaMovie"').where('"CinemaId" ='+ ctx.params.id).query();

});


app.use(koaRouter.routes());


app.listen(3000);