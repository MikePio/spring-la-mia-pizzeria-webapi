<script>
export default {
  name: 'Footer',
  data(){
    return{

    }
  }

}
</script>

<template>
  <footer class="bg-dark text-center text-white" style="height: 150px; max-height: 150px;">

    <!-- Grid container -->
    <div class="container p-4 pb-0">
        <!-- Section: Social media -->
        <section class="mb-4">
            
            <!-- Facebook -->
            <!-- <a style="padding: 7px 15px;" class="btn btn-outline-light btn-floating m-1 rounded-circle" href="#!" role="button"><i class="fab fa-facebook-f"></i></a> -->

            <!-- Twitter -->
            <!-- <a class="btn btn-outline-light btn-floating m-1 rounded-circle" href="#!" role="button"><i class="fab fa-twitter"></i></a> -->

            <!-- Google -->
            <!-- <a class="btn btn-outline-light btn-floating m-1 rounded-circle" href="#!" role="button"><i class="fab fa-google"></i></a> -->
            
            <!-- Instagram -->
            <a class="btn btn-outline-light btn-floating m-1 rounded-circle"
                href="https://www.instagram.com/michelepiopilla/" role="button"><i class="fab fa-instagram"></i></a>

            <!-- Linkedin -->
            <a class="btn btn-outline-light btn-floating m-1 rounded-circle" href="https://www.linkedin.com/in/michele-pilla" role="button"><i
                    class="fab fa-linkedin-in"></i></a>

            <!-- Github -->
            <a class="btn btn-outline-light btn-floating m-1 rounded-circle" href="https://github.com/MikePio"
                role="button"><i class="fab fa-github"></i></a>

        </section>
        <!-- Section: Social media -->
    </div>
    
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2023 By
        <a class="text-white" href="https://github.com/MikePio">@MikePio</a>
    </div>

  </footer>
</template>

<style lang="scss" scoped>
// @use '../scss/main.scss' as *;

</style>