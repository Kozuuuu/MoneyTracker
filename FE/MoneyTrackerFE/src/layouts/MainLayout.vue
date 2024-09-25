<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title> Money Tracker </q-toolbar-title>

        <div>
          <button id="nightModeToggle">
            <i id="icon" class="fas fa-sun"></i>
          </button>
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
      <q-list>
        <q-item-label header> <strong> Money Tracker </strong></q-item-label>
      </q-list>

      <SideBar />
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref } from "vue";
import SideBar from "../components/SideBar.vue";

// Sidebar open/close state
const leftDrawerOpen = ref(false);

// Function to toggle the sidebar
function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value;
}
</script>
