/**
 * Created by kuulart on 16.15.3.
 */
/**
 * Created by kuulart on 16.15.3.
 */
/*global define*/
define([
    'underscore',
    'backbone',
    'models/client'
], function (_, Backbone, Client) {
    'use strict';

    var Clients = Backbone.Collection.extend({
        url: '/clients',
        model: Client
    });


    return Clients;
});