<script>
export default {
  name: 'Loader',
};
</script>

<template>
  <div class="d-flex align-items-center justify-content-center m-5">
    <div class="loader m-5"></div>
  </div>
</template>

<style lang="scss" scoped>
@use '../../scss/main.scss' as *;
//* loader 1 - 3 puntini
.loader, .loader:before, .loader:after {
  border-radius: 50%;
  width: 2.5em;
  height: 2.5em;
  animation-fill-mode: both;
  animation: bblFadInOut 1.8s infinite ease-in-out;
}
.loader {
  // color: #FFF;
  // color: #4338CA;
  color: #ff001e;
  font-size: 7px;
  position: relative;
  text-indent: -9999em;
  transform: translateZ(0);
  animation-delay: -0.16s;
}
.loader:before,
.loader:after {
  content: '';
  position: absolute;
  top: 0;
}
.loader:before {
  left: -3.5em;
  animation-delay: -0.32s;
}
.loader:after {
  left: 3.5em;
}

@keyframes bblFadInOut {
  0%, 80%, 100% { box-shadow: 0 2.5em 0 -1.3em }
  40% { box-shadow: 0 2.5em 0 0 }
}

//* loader 2 - bello ma lento
// .loader {
//   width: calc(100px - 24px);
//   height: 50px;
//   position: relative;
//   animation: flippx 2s infinite linear;
// }
// .loader:before {
//   content: "";
//   position: absolute;
//   inset: 0;
//   margin: auto;
//   width: 20px;
//   height: 20px;
//   border-radius: 50%;
//   // background: #FFF;
//   background: #4338CA;
//   transform-origin: -24px 50%;
//   animation: spin 1s infinite linear;
// }
// .loader:after {
//   content: "";
//   position: absolute;
//   left: 50%;
//   top: 50%;
//   transform: translate(-50% , -50%);
//   // background: #fff;
//   background: #4338CA;
//   width: 48px;
//   height: 48px;
//   border-radius: 50%;
// }

// @keyframes flippx {
//   0%, 49% {
//     transform: scaleX(1);
//   }
//   50%, 100% {
//     transform: scaleX(-1);
//   }
// }
// @keyframes spin {
//   100% {
//     transform: rotate(360deg);
//   }
// }

//* loader 3 
// .loader {
//         transform: rotateZ(45deg);
//         perspective: 1000px;
//         border-radius: 50%;
//         width: 48px;
//         height: 48px;
//         // color: #fff;
//         color: #4338CA;
//       }
//         .loader:before,
//         .loader:after {
//           content: '';
//           display: block;
//           position: absolute;
//           top: 0;
//           left: 0;
//           width: inherit;
//           height: inherit;
//           border-radius: 50%;
//           transform: rotateX(70deg);
//           animation: 1s spin linear infinite;
//         }
//         .loader:after {
//           // color: #FF3D00;
//           color: #4338CA;
//           transform: rotateY(70deg);
//           // animation-delay: .4s;
//           animation-delay: .2s;
//         }

//       @keyframes rotate {
//         0% {
//           transform: translate(-50%, -50%) rotateZ(0deg);
//         }
//         100% {
//           transform: translate(-50%, -50%) rotateZ(360deg);
//         }
//       }

//       @keyframes rotateccw {
//         0% {
//           transform: translate(-50%, -50%) rotate(0deg);
//         }
//         100% {
//           transform: translate(-50%, -50%) rotate(-360deg);
//         }
//       }

//       @keyframes spin {
//         0%,
//         100% {
//           box-shadow: .2em 0px 0 0px currentcolor;
//         }
//         12% {
//           box-shadow: .2em .2em 0 0 currentcolor;
//         }
//         25% {
//           box-shadow: 0 .2em 0 0px currentcolor;
//         }
//         37% {
//           box-shadow: -.2em .2em 0 0 currentcolor;
//         }
//         50% {
//           box-shadow: -.2em 0 0 0 currentcolor;
//         }
//         62% {
//           box-shadow: -.2em -.2em 0 0 currentcolor;
//         }
//         75% {
//           box-shadow: 0px -.2em 0 0 currentcolor;
//         }
//         87% {
//           box-shadow: .2em -.2em 0 0 currentcolor;
//         }
//       }
</style>