const str = []
const url = []
const prompt = require("prompt-async");
const { Cluster } = require('puppeteer-cluster');
const fs = require('fs');
const num = 5
const loopNum = 2*num
let j=0;
let i=0;
async function example_async() // Available only with `prompt-async`!
      {
          // Start the prompt.
          prompt.start();
          (async () => {
            const {inputUrl} = await prompt.get(["inputUrl"]);
            const url1 = inputUrl.split(",");
            while(i<loopNum){
              url.push(url1[i]);
              str.push(url1[i+1]);
              i=i+2;
              
            }
            const cluster = await Cluster.launch({
              puppeteerOptions: {
                  headless: false
                  //defaultViewport: null, 
              },
              concurrency: Cluster.CONCURRENCY_PAGE,
              maxConcurrency: 2,
            });
        let j=0;
        await cluster.task(async ({ page, data: url}) => {
          await page.goto(url);
          const outerHtml = await page.$eval('body',el => el.outerHTML)
          // let file = str[j];
          // console.log(file)
          fs.writeFile(str[j++], outerHtml, { flag: 'a+' }, err => {})
        });
        
        cluster.queue(url[0]);
        cluster.queue(url[1]);
        cluster.queue(url[2]);
        cluster.queue(url[3]);
        cluster.queue(url[4]);
        
       
        await cluster.idle();
        await cluster.close();
      })();
      }
async function error_handling_async()
{
  try
          {
              await example_async();
          }
          catch(error)
          {
              console.error("An error occurred: ", error);
          }
          
      }
       
error_handling_async();




    
    
      