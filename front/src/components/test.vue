<template>
  <div>
    
  <!-- Header -->
  <header class="" >
    <div class="container text-center my-auto">
      <h3 style="margin-top: 30%;" class="mb-5 g">
        <div>오늘의 확진자</div>
      </h3>

      <h3 style="margin-top: 1%;" class="g">
        <div :style="{ color: activeColor }"> {{result.decideGapCnt | comma}} 명</div>
        <div style="font-size:13px; margin-top: 1%;">({{result.createDt}}일 기준)</div>
      </h3>

      <h3 style="margin-top: 3%;" class="g">
        <div style="font-size:13px"> 누적 : {{result.decideCnt | comma}} </div>
      </h3>
      
      <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_jvybumrx.json"  background="transparent"  speed="1"  style="margin-left:auto; margin-right:auto; width: 220px; height: 220px;"  loop autoplay></lottie-player>

    </div>
  </header>

  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      result: {},
      activeColor: 'orange',

    };
  },
  filters:{
    comma(val){
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },
  created() {

    this.getData();
  },
  methods: {
    getData(){
          this.$axios.get('/api/data').then(response => {
          this.result = response.data

          if(this.result.decideGapCnt > 1000){
            this.activeColor = 'red'
          } else if(this.result.decideGapCnt > 500){
            this.activeColor = 'yellow'
          } else {
            this.activeColor = 'blue'
          }

      }).catch((ex) => {
          console.warn("ERROR!!!!! : ",ex)
      })
    },
  },
}
</script>

<style>
  .a { font-family: Georgia, serif; }
  .b { font-family: serif; }
  .c { font-family: sans-serif; }
  .d { font-family: monospace; }
  .e { font-family: cursive; }
  .f { font-family: fantasy; }
  .g { font-family: 'BMHANNAAir'; }

 @font-face {
    font-family: 'BMHANNAAir';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.0/BMHANNAAir.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>