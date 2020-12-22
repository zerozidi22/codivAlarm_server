<template>
  <div>
    
  <!-- Header -->
  <header class="">
    <div class="container text-center my-auto">
      <h3 style="margin-top: 30%" class="mb-5 g">
        <div>오늘의 확진자</div>
      </h3>

      <h3 style="margin-top: 5%" class="mb-5 g">
        <div style="font-family: Georgia, 맑은 고딕:">1000명 입니다.</div>
        <div style="font-size:4px">(일 기준)</div>
      </h3>
      <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_jvybumrx.json"  background="transparent"  speed="1"  style="margin-left:auto; margin-right:auto; width: 220px; height: 220px;"  loop autoplay></lottie-player>

    </div>
    <div class="overlay"></div>
    
  </header>

  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
    data() {
    return {
    };
  },
  methods: {
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