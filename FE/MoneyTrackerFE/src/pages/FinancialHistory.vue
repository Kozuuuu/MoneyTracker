<template>
  <q-page>
    <q-spinner v-if="loading" size="30px" color="primary" />
    <q-table
      v-if="!loading"
      :rows="records"
      :columns="columns"
      row-key="payroll"
    >
      <!-- Custom table slots for rendering data... -->
    </q-table>
  </q-page>
</template>

<script setup>
import axios from "axios";
import { onMounted, ref } from "vue";

const records = ref([]);
const loading = ref(true); // Loading state
const columns = [
  { name: "period", label: "Period", field: "period", align: "left" },
  { name: "date", label: "Date", field: "date", align: "left" },
  { name: "cash", label: "Cash", field: "cash", align: "right" },
  { name: "savings", label: "Savings", field: "savings", align: "right" },
  { name: "expenses", label: "Expenses", field: "expenses", align: "right" },
  { name: "extra", label: "Extra", field: "extra", align: "right" },
];

onMounted(() => {
  axios
    .get("http://localhost:7700/records")
    .then((response) => {
      records.value = response.data;
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    })
    .finally(() => {
      loading.value = false; // Stop loading
    });
});
</script>
