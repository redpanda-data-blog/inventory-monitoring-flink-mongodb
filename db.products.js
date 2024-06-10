db.products.insertMany([
  {
    itemId: "PER-00001",
    itemName: "Bananas (Cavendish)",
    itemType: "Produce",
    supplier: {
      supplierId: "SUP-00001",
      name: "Tropical Fruitz Ltd",
      contact: {
        email: "supplier@tropicalfruitz.com",
        phone: "+1 (555) 555-5555",
      },
    },
    unitPrice: 2.5,
    unitOfMeasure: "kg",
    reorderPoint: 5,
    reorderQuantity: 50,
    perishable: true,
    batches: [
      {
        batchId: "PER-00001-A",
        purchaseDate: "2024-04-21",
        expiryDate: "2024-04-25",
        currentStock: 25,
        discount: {
          enabled: true,
          discountType: "percentage",
          discountValue: 0.0,
        },
      },
    ],
  },
]);
