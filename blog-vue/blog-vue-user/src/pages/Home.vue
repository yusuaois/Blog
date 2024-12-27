<template>
    <div>
        <navbar></navbar>
        <div class="container">
            <el-row :gutter="30">
                <el-col :sm="24" :md="16" style="transition:all .5s ease-out;margin-bottom:30px;">
                    <articleList></articleList>
                </el-col>
                <el-col :sm="24" :md="8">
                    <right></right>
                </el-col>
            </el-row>
        </div>
    </div>
</template>


<script setup>
import header from '../components/header.vue'
import articlelist from '../components/articlelist.vue'
import rightlist from '../components/rightlist.vue'
defineOptions({
    name: 'Home',
    components:{
        'navbar':header,
        'articleList':articlelist,
        'right':rightlist,
    },
    created(){
        // alert('home')
    },
})
</script>
<style scoped></style>