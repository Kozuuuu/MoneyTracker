<template>
  <q-list>
    <!-- Add other navigation items here -->
    <q-item clickable tag="router-link" to="/dashboard">
      <q-item-section avatar>
        <q-icon name="dashboard" />
      </q-item-section>
      <q-item-section>Dashboard</q-item-section>
    </q-item>
    <!-- Financial History Item -->
    <q-item clickable tag="router-link" to="/history">
      <q-item-section avatar>
        <q-icon name="history" />
      </q-item-section>
      <q-item-section>Financial History</q-item-section>
    </q-item>
  </q-list>
</template>

<script setup>
// No script needed if it's just a static sidebar
</script>
