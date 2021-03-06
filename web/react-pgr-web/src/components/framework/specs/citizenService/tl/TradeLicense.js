var dat = {
    "tl.create": {
        "numCols": 12 / 3,
        "url": "/tl-services/license/v1/_create",
        "useTimestamp": true,
        "tenantIdRequired": false,
        "objectName": "licenses",
        "idJsonPath": "licenses[0].id",
        "groups": [{
                "label": "tl.create.licenses.groups.TradeOwnerDetails",
                "name": "TradeOwnerDetails",
                "fields": [{
                        "name": "AadharNumber",
                        "jsonPath": "licenses[0].adhaarNumber",
                        "label": "tl.create.licenses.groups.TradeOwnerDetails.AadharNumber",
                        "pattern": "",
                        "type": "aadhar",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Aadhar Number (12 Digit Number)",
                        "maxLength": "12",
                        "defaultValue": null
                    },
                    {
                        "name": "MobileNumber",
                        "jsonPath": "licenses[0].mobileNumber",
                        "label": "tl.create.licenses.groups.TradeOwnerDetails.Mobile Number",
                        "pattern": "",
                        "type": "mobileNumber",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Mobile Number (10 Digit Number)"
                    },
                    {
                        "name": "TradeOwnerName",
                        "jsonPath": "licenses[0].ownerName",
                        "label": "tl.create.licenses.groups.TradeOwnerDetails.TradeOwnerName",
                        "pattern": "^.[a-zA-Z. ]{2,99}$",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Trade Owner Name (Min:3, Max:100)",
                        "maxLength": "100"
                    },
                    {
                        "name": "FatherSpouseName",
                        "jsonPath": "licenses[0].fatherSpouseName",
                        "label": "tl.create.licenses.groups.TradeOwnerDetails.FatherSpouseName",
                        "pattern": "^.[a-zA-Z. ]{2,99}$",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Father/Spouse Name (Min:3, Max:100)",
                        "maxLength": "100"
                    },
                    {
                        "name": "EmailID",
                        "jsonPath": "licenses[0].emailId",
                        "label": "tl.create.licenses.TradeOwnerDetails.groups.EmailID",
                        "pattern": "",
                        "type": "email",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Email ID (ex: abc@xyz.com, Max: 50)",
                        "maxLength": "50"
                    },
                    {
                        "name": "TradeOwnerAddress",
                        "jsonPath": "licenses[0].ownerAddress",
                        "label": "tl.create.licenses.groups.TradeOwnerDetails.TradeOwnerAddress",
                        "pattern": "^[a-zA-Z0-9:@&*_+#()/,. -]*$",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Trade Owner Address (Max:250)",
                        "maxLength": "250"
                    }
                ]
            },

            {
                "label": "tl.create.licenses.groups.TradeLocationDetails",
                "name": "TradeLocationDetails",
                "fields": [{
                        "name": "PropertyAssessmentNo",
                        "jsonPath": "licenses[0].propertyAssesmentNo",
                        "label": "tl.create.licenses.groups.TradeLocationDetails.PropertyAssessmentNo",
                        "pattern": "^[a-zA-Z0-9/-]*$",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Property Assessment Number"
                    },
                    {
                        "name": "Locality",
                        "jsonPath": "licenses[0].localityId",
                        "label": "tl.create.licenses.groups.TradeLocationDetails.Locality",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "/egov-location/boundarys/boundariesByBndryTypeNameAndHierarchyTypeName?&boundaryTypeName=LOCALITY&hierarchyTypeName=LOCATION|$..id|$..name",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "adminWardId",
                        "jsonPath": "licenses[0].adminWardId",
                        "label": "tl.create.licenses.groups.TradeLocationDetails.adminWardId",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "/egov-location/boundarys/boundariesByBndryTypeNameAndHierarchyTypeName?tenantId=default&boundaryTypeName=Ward&hierarchyTypeName=ADMINISTRATION|$..id|$..name",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "revenueWardId",
                        "jsonPath": "licenses[0].revenueWardId",
                        "label": "tl.create.licenses.groups.TradeLocationDetails.revenueWardId",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "/egov-location/boundarys/boundariesByBndryTypeNameAndHierarchyTypeName?&boundaryTypeName=WARD&hierarchyTypeName=REVENUE|$..id|$..name",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "OwnershipType",
                        "jsonPath": "licenses[0].ownerShipType",
                        "label": "tl.create.licenses.groups.TradeLocationDetails.OwnershipType",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "defaultValue": [{
                                "key": "STATE_GOVERNMENT",
                                "value": "STATE GOVERNMENT"
                            },
                            {
                                "key": "OWNED",
                                "value": "OWNED"
                            },
                            {
                                "key": "RENTED",
                                "value": "RENTED"
                            },
                            {
                                "key": "CENTRAL_GOVERNMENT",
                                "value": "CENTRAL GOVERNMENT"
                            },
                            {
                                "key": "ULB",
                                "value": "ULB"
                            }
                        ]
                    },
                    {
                        "name": "TradeAddress",
                        "jsonPath": "licenses[0].tradeAddress",
                        "label": "tl.create.licenses.groups.TradeLocationDetails.TradeAddress",
                        "pattern": "^[a-zA-Z0-9:@&*_+#()/,. -]*$",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Trade Address (Max:250)",
                        "maxLength": "250"
                    }
                ]
            },
            {
                "label": "tl.create.licenses.groups.TradeDetails",
                "name": "TradeDetails",
                "fields": [{
                        "name": "TradeTitle",
                        "jsonPath": "licenses[0].tradeTitle",
                        "label": "tl.create.licenses.groups.TradeDetails.TradeTitle",
                        "pattern": "^[a-zA-Z0-9@:()/#,. -]*$",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Trade Title (Max: 250)",
                        "maxLength": "250"
                    },
                    {
                        "name": "TradeType",
                        "jsonPath": "licenses[0].tradeType",
                        "label": "tl.create.licenses.groups.TradeDetails.TradeType",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "defaultValue": [{
                                "key": "PERMANENT",
                                "value": "PERMANENT"
                            },
                            {
                                "key": "TEMPORARY",
                                "value": "TEMPORARY"
                            }
                        ]

                    },
                    {
                        "name": "TradeCategory",
                        "jsonPath": "licenses[0].categoryId",
                        "label": "tl.create.licenses.groups.TradeDetails.TradeCategory",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "/tl-masters/category/v1/_search?tenantId=default&type=category|$..id|$..name",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "depedants": [{
                            "jsonPath": "licenses[0].subCategoryId",
                            "type": "dropDown",
                            "pattern": "/tl-masters/category/v1/_search?tenantId=default&type=subcategory&categoryId={licenses[0].categoryId}|$.categories.*.id|$.categories.*.name"
                        }]
                    },
                    {
                        "name": "TradeSubCategory",
                        "jsonPath": "licenses[0].subCategoryId",
                        "label": "tl.create.licenses.groups.TradeDetails.TradeSubCategory",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "depedants": [{
                                "jsonPath": "licenses[0].uomName",
                                "type": "text",
                                "isRequired": true,
                                "isDisabled": true,
                                "pattern": ""
                            },
                            {
                                "jsonPath": "licenses[0].validityYears",
                                "type": "text",
                                "isRequired": true,
                                "isDisabled": true,
                                "pattern": ""
                            }
                        ]
                    },
                    {
                        "name": "UOM",
                        "jsonPath": "licenses[0].uomName",
                        "label": "tl.create.licenses.groups.TradeDetails.UOM",
                        "pattern": "",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": true,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "validity",
                        "jsonPath": "licenses[0].validityYears",
                        "label": "tl.create.licenses.groups.validity",
                        "pattern": "",
                        "url": "/tl-masters/category/v1/_search?tenantId=default&ids=1",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": true,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "Remarks",
                        "jsonPath": "licenses[0].remarks",
                        "label": "tl.create.licenses.groups.TradeDetails.Remarks",
                        "pattern": "^[a-zA-Z0-9:@&*_+#()/,. -]*$",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "maxLength": "1000"
                    },
                    {
                        "name": "TradeCommencementDate",
                        "jsonPath": "licenses[0].tradeCommencementDate",
                        "label": "tl.create.licenses.groups.TradeDetails.TradeCommencementDate",
                        "pattern": "",
                        "type": "datePicker",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter in dd/mm/yyyy Format",
                        "maxLength": "10"
                    },
                    {
                        "name": "TradeOwner",
                        "jsonPath": "licenses[0].isPropertyOwner",
                        "label": "tl.create.licenses.groups.TradeDetails.TraderOwnerProperty",
                        "pattern": "",
                        "type": "checkbox",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "defaultValue": false,
                        "showHideFields": [{
                            "ifValue": true,
                            "hide": [],
                            "show": [{
                                "name": "createLicenseCategoryType",
                                "isGroup": true,
                                "isField": false
                            }]
                        }]
                    },
                    {
                        "name": "UOM ID",
                        "jsonPath": "licenses[0].uomId",
                        "label": "tl.create.licenses.groups.TradeDetails.UOM",
                        "pattern": "",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "hide": "true"
                    },

                    {
                        "name": "expiryDate",
                        "jsonPath": "licenses[0].expiryDate",
                        "label": "tl.create.licenses.groups.TradeDetails.expiryDate",
                        "pattern": "",
                        "type": "datePicker",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter in dd/mm/yyyy Format",
                        "maxLength": "10",
                        "hide": "true"
                    }
                ]
            },

            {
                "label": "tl.create.licenses.groups.agreementDetails",
                "name": "createLicenseCategoryType",
                "hide": true,
                "fields": [{
                        "name": "dateOfExecution",
                        "jsonPath": "licenses[0].agreementDate",
                        "label": "tl.create.licenses.groups.agreementDetails.agreementDate",
                        "pattern": "",
                        "type": "datePicker",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter in dd/mm/yyyy Format",
                        "maxLength": "10"
                    },
                    {
                        "name": "agreementNo",
                        "jsonPath": "licenses[0].agreementNo",
                        "label": "tl.create.licenses.groups.agreementDetails.agreementNo",
                        "pattern": "^[a-zA-Z0-9&/()-]*$",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Agreement No (Max:30, Alpha/Numeric)",
                        "maxLength": "30"
                    }
                ]
            }
        ],
        "feeDetails": [{
            "id": null,
            "tenantId": localStorage.getItem("tenantId"),
            "consumerCode": "",
            "consumerType": "consumertype1",
            "businessService": "CS",
            "minimumAmountPayable": 10,
            "owner": {
                "id": 0,
                "userName": null,
                "name": null,
                "type": null,
                "mobileNumber": null,
                "emailId": null,
                "roles": null
            },
            "taxPeriodFrom": 0,
            "taxPeriodTo": 0,
            "demandDetails": [{
                "id": null,
                "demandId": null,
                "taxHeadMasterCode": "",
                "taxAmount": 20,
                "collectionAmount": 0
            }]
        }]
    },

    "tl.view": {
        "numCols": 12 / 3,
        "url": "/tl-services/license/v1/_search?ids={id}",
        "tenantIdRequired": true,
        "useTimestamp": true,
        "objectName": "licenses",
        "label": "tl.view.groups.title",
        "groups": [
            {
                "label": "Service Details",
                "name": "srndetails",
                "fields": [{
                        "name": "serviceRequestId",
                        "jsonPath": "licenses[0].serviceRequestId",
                        "label": "Service Request Number",
                        "pattern": "",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Aadhar Number must be of 12 digits"
                    }
                ]
            },
            {
                "label": "tl.view.groups.tradeOwnerDetails",
                "name": "viewCategoryType",
                "fields": [{
                        "name": "aadharNumber",
                        "jsonPath": "licenses[0].adhaarNumber",
                        "label": "tl.view.groups.aadharNumber",
                        "pattern": "^.{12,12}$",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Aadhar Number must be of 12 digits"
                    },
                    {
                        "name": "mobileNumber",
                        "jsonPath": "licenses[0].mobileNumber",
                        "label": "tl.view.groups.mobileNumber",
                        "pattern": "^.{10,10}$",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Mobile Number must be of 10 digits"
                    },
                    {
                        "name": "tradeOwnerName",
                        "jsonPath": "licenses[0].ownerName",
                        "label": "tl.view.groups.tradeOwnerName",
                        "pattern": "^.[a-zA-Z. ]{2,49}$",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Name"
                    },
                    {
                        "name": "fatherSpouseName",
                        "jsonPath": "licenses[0].fatherSpouseName",
                        "label": "tl.view.groups.fatherSpouseName",
                        "pattern": "",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "emailId",
                        "jsonPath": "licenses[0].emailId",
                        "label": "tl.view.groups.emailId",
                        "pattern": "^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+$",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid EmailID"
                    },
                    {
                        "name": "tradeOwnerAddress",
                        "jsonPath": "licenses[0].ownerAddress",
                        "label": "tl.view.groups.tradeOwnerAddress",
                        "pattern": "",
                        "type": "textarea",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    }
                ]
            },
            {
                "label": "tl.licenses.view.groups.TradeLocationDetails",
                "name": "TradeLocationDetails",
                "fields": [{
                        "name": "PropertyAssessmentNo",
                        "jsonPath": "licenses[0].propertyAssesmentNo",
                        "label": "tl.licenses.view.groups.PropertyAssessmentNo",
                        "pattern": "",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "Locality",
                        "jsonPath": "licenses[0].localityId",
                        "label": "tl.licenses.view.groups.Locality",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "/egov-location/boundarys/boundariesByBndryTypeNameAndHierarchyTypeName?&boundaryTypeName=LOCALITY&hierarchyTypeName=LOCATION|$..id|$..name",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "adminWardId",
                        "jsonPath": "licenses[0].adminWardId",
                        "label": "tl.licenses.view.groups.adminWardId",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "/egov-location/boundarys/boundariesByBndryTypeNameAndHierarchyTypeName?tenantId=default&boundaryTypeName=Ward&hierarchyTypeName=ADMINISTRATION|$..id|$..name",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "revenueWardId",
                        "jsonPath": "licenses[0].revenueWardId",
                        "label": "tl.licenses.view.groups.revenueWardId",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "/egov-location/boundarys/boundariesByBndryTypeNameAndHierarchyTypeName?&boundaryTypeName=WARD&hierarchyTypeName=REVENUE|$..id|$..name",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "OwnershipType",
                        "jsonPath": "licenses[0].ownerShipType",
                        "label": "tl.licenses.view.groups.OwnershipType",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "defaultValue": [{
                                "key": "STATE_GOVERNMENT",
                                "value": "STATE GOVERNMENT"
                            }, {
                                "key": "OWNED",
                                "value": "OWNED"
                            }, {
                                "key": "RENTED",
                                "value": "RENTED"
                            }, {
                                "key": "CENTRAL_GOVERNMENT",
                                "value": "CENTRAL GOVERNMENT"
                            }, {
                                "key": "ULB",
                                "value": "ULB"
                            }
                        ]
                    },
                    {
                        "name": "TradeAddress",
                        "jsonPath": "licenses[0].tradeAddress",
                        "label": "tl.licenses.view.groups.TradeAddress",
                        "pattern": "",
                        "type": "textarea",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    }
                ]
            },

            {
                "label": "tl.view.licenses.groups.TradeDetails",
                "name": "TradeDetails",
                "fields": [{
                        "name": "TradeTitle",
                        "jsonPath": "licenses[0].tradeTitle",
                        "label": "tl.view.licenses.groups.TradeTitle",
                        "pattern": "",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "TradeType",
                        "jsonPath": "licenses[0].tradeType",
                        "label": "tl.view.licenses.groups.TradeType",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "defaultValue": [{
                                "key": "PERMANENT",
                                "value": "PERMANENT"
                            },
                            {
                                "key": "TEMPORARY",
                                "value": "TEMPORARY"
                            }
                        ]

                    },
                    {
                        "name": "TradeCategory",
                        "jsonPath": "licenses[0].categoryId",
                        "label": "tl.view.licenses.groups.TradeCategory",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "/tl-masters/category/v1/_search?|$..id|$..name",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "TradeSubCategory",
                        "jsonPath": "licenses[0].subCategoryName",
                        "label": "tl.view.licenses.groups.TradeSubCategory",
                        "pattern": "",
                        "type": "singleValueList",
                        "url": "",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "UOM",
                        "jsonPath": "licenses[0].uomName",
                        "label": "tl.view.licenses.groups.UOM",
                        "pattern": "",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "validity",
                        "jsonPath": "licenses[0].validityYears",
                        "label": "tl.view.licenses.groups.validity",
                        "pattern": "",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "Remarks",
                        "jsonPath": "licenses[0].remarks",
                        "label": "tl.view.licenses.groups.Remarks",
                        "pattern": "",
                        "type": "text",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "TradeCommencementDate",
                        "jsonPath": "licenses[0].tradeCommencementDate",
                        "label": "tl.view.licenses.groups.TradeCommencementDate",
                        "pattern": "",
                        "type": "datePicker",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": ""
                    },
                    {
                        "name": "TradeOwner",
                        "jsonPath": "licenses[0].isPropertyOwner",
                        "label": "tl.create.licenses.groups.TradeDetails.TraderOwnerProperty",
                        "pattern": "",
                        "type": "checkbox",
                        "isRequired": false,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "",
                        "defaultValue": false,
                        "showHideFields": [{
                            "ifValue": true,
                            "hide": [],
                            "show": [{
                                "name": "createLicenseCategoryType",
                                "isGroup": true,
                                "isField": false
                            }]
                        }]
                    },
                    {
                        "name": "expiryDate",
                        "jsonPath": "licenses[0].expiryDate",
                        "label": "tl.view.licenses.groups.TradeDetails.expiryDate",
                        "pattern": "",
                        "type": "datePicker",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter in dd/mm/yyyy Format",
                        "maxLength": "10",
                        "isHidden": true
                    }
                ]
            },
            {
                "label": "tl.create.licenses.groups.agreementDetails",
                "name": "createLicenseCategoryType",
                "hide": true,
                "fields": [{
                        "name": "dateOfExecution",
                        "jsonPath": "licenses[0].agreementDate",
                        "label": "tl.create.licenses.groups.agreementDetails.agreementDate",
                        "pattern": "",
                        "type": "datePicker",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter in dd/mm/yyyy Format",
                        "maxLength": "10"
                    },
                    {
                        "name": "agreementNo",
                        "jsonPath": "licenses[0].agreementNo",
                        "label": "tl.create.licenses.groups.agreementDetails.agreementNo",
                        "pattern": "^[a-zA-Z0-9&/()-]*$",
                        "type": "text",
                        "isRequired": true,
                        "isDisabled": false,
                        "requiredErrMsg": "",
                        "patternErrMsg": "Enter Valid Agreement No (Max:30, Alpha/Numeric)",
                        "maxLength": "30"
                    }
                ]
            }
        ]
    }
}

export default dat;